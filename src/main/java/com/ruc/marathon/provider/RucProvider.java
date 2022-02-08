package com.ruc.marathon.provider;

import com.ruc.marathon.domain.RucEntity;
import com.ruc.marathon.dto.ConsultaRucDto;

public interface RucProvider {
    ConsultaRucDto getRuc(String ruc);
}
