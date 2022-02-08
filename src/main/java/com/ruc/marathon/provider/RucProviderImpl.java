package com.ruc.marathon.provider;

import com.ruc.marathon.common.Constants;
import com.ruc.marathon.domain.RucEntity;
import com.ruc.marathon.dto.ConsultaRucDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class RucProviderImpl implements RucProvider{
    private final RestTemplate restTemplate;
    @Autowired
    public RucProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public ConsultaRucDto getRuc(String ruc) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> requestEntity  = new HttpEntity<String>(headers);
        ResponseEntity<ConsultaRucDto> response = this.restTemplate.exchange(Constants.Api_Ruc, HttpMethod.GET,null, ConsultaRucDto.class,"2",ruc,Constants.Token_Ruc);
        ConsultaRucDto result = response.getBody();
        return result;
    }
}
