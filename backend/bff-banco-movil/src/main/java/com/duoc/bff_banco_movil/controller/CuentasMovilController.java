package com.duoc.bff_banco_movil.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.bff_banco_movil.model.Cuenta;
import com.duoc.bff_banco_movil.model.CuentaResponse;
import com.duoc.bff_banco_movil.service.CuentasMovilService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/banco/movil/cuentas")
public class CuentasMovilController {

    private final CuentasMovilService cuentasService;

    public CuentasMovilController(CuentasMovilService cuentasService) {
        this.cuentasService = cuentasService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt){
        return ResponseEntity.ok(
            cuentasService.findAll(jwt)
            .stream()
            .map(CuentaResponse::new)
            .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCuenta(
        @PathVariable Long id,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ){
        Cuenta encontrado = cuentasService.findCuenta(id, jwt);
        return (encontrado == null) ?
            ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
            ResponseEntity.ok(new CuentaResponse(encontrado));
    }

    @PostMapping
    public ResponseEntity<?> saveCuenta(
        @Valid @RequestBody Cuenta cuenta,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ){
        return ResponseEntity.ok(new CuentaResponse(cuentasService.saveCuenta(cuenta, jwt)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(
        @PathVariable Long id,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ){
        return (cuentasService.deleteCuenta(id, jwt)) ?
            ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
