package com.ruc.marathon.controller;
import com.ruc.marathon.common.ResponeResult;
import com.ruc.marathon.dto.ConsultaRucDto;
import com.ruc.marathon.provider.RucProvider;
import com.ruc.marathon.service.RucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ruc")

public class ApiRucController {
    private RucProvider rucProvider;
    private RucService rucService;
    @Autowired
    public ApiRucController(RucProvider rucProvider,
                            RucService rucService
                         ){
        this.rucProvider = rucProvider;
        this.rucService = rucService;
    }
    @GetMapping( "/{ruc}")
    public ResponseEntity<ResponeResult> getRuc(@PathVariable String ruc){
        try {
            ConsultaRucDto data = this.rucProvider.getRuc(ruc);
            ResponeResult<ConsultaRucDto> result = new ResponeResult<>(true,"Ok",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping( "/get-all")
    public ResponseEntity<ResponeResult> getAll(){
        try {
            List<ConsultaRucDto> data = this.rucService.getAll();

            ResponeResult<List<ConsultaRucDto>> result = new ResponeResult<>(true,"Ok",data);

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<ResponeResult> register(@RequestBody ConsultaRucDto ruc){
        try {
            ResponeResult<ConsultaRucDto> result = this.rucService.register(ruc);

            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{ruc}")
    public ResponseEntity<ResponeResult> register(@PathVariable String ruc){
        try {
            ResponeResult<ConsultaRucDto> result = this.rucService.delete(ruc);

            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

