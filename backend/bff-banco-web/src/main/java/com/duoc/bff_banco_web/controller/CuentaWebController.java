package com.duoc.bff_banco_web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.bff_banco_web.model.Cuenta;
import com.duoc.bff_banco_web.service.CuentaWebService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/banco/web/cuentas")
public class CuentaWebController {

    private final CuentaWebService cuentaService;

    public CuentaWebController(CuentaWebService cuentaService){
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt){
        return ResponseEntity.ok(cuentaService.findAll(jwt));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCuenta(
        @PathVariable Long id,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ){
        Cuenta encontrado = cuentaService.findCuenta(id, jwt);
        return (encontrado == null) ?
            ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
            ResponseEntity.ok(encontrado);
    }

    @PostMapping
    public ResponseEntity<?> saveCuenta(
        @Valid @RequestBody Cuenta cuenta,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ){
        return ResponseEntity.ok(cuentaService.saveCuenta(cuenta, jwt));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCuenta(
        @PathVariable Long id, 
        @Valid @RequestBody Cuenta cuenta,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt){
        return ResponseEntity.ok(cuentaService.updateCuenta(id, cuenta, jwt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(
        @PathVariable Long id,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ){
        return (cuentaService.deleteCuenta(id, jwt)) ?
            ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



    

}
