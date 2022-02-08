package com.ruc.marathon.service;

import com.ruc.marathon.common.ResponeResult;
import com.ruc.marathon.domain.RucEntity;
import com.ruc.marathon.dto.ConsultaRucDto;
import com.ruc.marathon.mapper.RucListConverter;
import com.ruc.marathon.repository.RucRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RucServiceImpl implements  RucService{
    private RucRepository rucRepository;
    @Autowired
    public  RucServiceImpl (RucRepository rucRepository){
        this.rucRepository = rucRepository;
    }
    @Override
    public List<ConsultaRucDto> getAll() {
        List<RucEntity> rucEntity = rucRepository.findAll();
        return new RucListConverter().convert(rucEntity);
    }
    @Override
    public ResponeResult register(ConsultaRucDto ruc) {
        RucEntity  exists =  rucRepository.findByRuc(ruc.getRuc());
        ResponeResult<ConsultaRucDto> result = new ResponeResult();
        if(exists == null){
            RucEntity rucEntity = rucRepository.save(new RucEntity(
                    ruc.getRuc(),
                    ruc.getRazon_social(),
                    ruc.getEstado(),
                    ruc.getDireccion(),
                    ruc.getUbigeo(),
                    ruc.getDepartamento(),
                    ruc.getProvincia(),
                    ruc.getDistrito()));
            ModelMapper modelMapper = new ModelMapper();

            result.data = modelMapper.map(rucEntity, ConsultaRucDto.class);
            result.success = true;
            result.message= "Persona jurídica registrada correctamente";

        }else{
            result.success = false;
            result.message = "La Persona Jurídica ya se encuentra registrada";
        }
        return result;
    }

    @Override
    public ResponeResult delete(String ruc) {
        RucEntity  exists =  rucRepository.findByRuc(ruc.trim());
        ResponeResult<ConsultaRucDto> result = new ResponeResult();
        if(exists != null){
            rucRepository.deleteByRuc(ruc);
            result.success = true;
            result.message= "Persona jurídica eliminada correctamente";

        }else{
            result.success = false;
            result.message = "La Persona Jurídica no se encuentra registrada";
        }
        return result;
    }
}
