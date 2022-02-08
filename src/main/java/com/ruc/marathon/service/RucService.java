package com.ruc.marathon.service;
import com.ruc.marathon.common.ResponeResult;
import com.ruc.marathon.dto.ConsultaRucDto;
import java.util.List;
public interface RucService {
    List<ConsultaRucDto> getAll();
    ResponeResult register(ConsultaRucDto ruc);
    ResponeResult delete(String ruc);
}
