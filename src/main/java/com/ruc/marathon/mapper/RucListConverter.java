package com.ruc.marathon.mapper;

import com.ruc.marathon.domain.RucEntity;
import com.ruc.marathon.dto.ConsultaRucDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.stream.Collectors;

public class RucListConverter  {
    public List<ConsultaRucDto> convert(List<RucEntity> source) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, new TypeToken<List<ConsultaRucDto>>() {}.getType());
    }
}
