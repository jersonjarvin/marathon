package com.ruc.marathon.controller;

import com.ruc.marathon.common.ResponeResult;
import com.ruc.marathon.dto.ConsultaRucDto;
import com.ruc.marathon.provider.RucProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {
    private RucProvider rucProvider;
    @Autowired
    public ApiAuthController(RucProvider rucProvider

    ){
        this.rucProvider = rucProvider;
    }
    @GetMapping( "/{username}/{password}")
    public ResponseEntity<ResponeResult> login(@PathVariable String username, @PathVariable String password){
        try {
            ResponeResult<String> result = new ResponeResult<>();
            if(username.equals("admin") && password.equals("admin")){
                result.data = "Administrador";
                result.message = "Éxito";
                result.success = true;
            }else {
                result.message = "La persona Jurídica no existe";
                result.success = false;
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
