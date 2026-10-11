package com.duoc.bff_banco_cajero.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.bff_banco_cajero.model.Cuenta;
import com.duoc.bff_banco_cajero.model.CuentaResponse;
import com.duoc.bff_banco_cajero.service.CuentasCajeroService;


@RestController
@RequestMapping("/api/banco/cajero/cuentas")
public class CuentasCajeroController {

    private final CuentasCajeroService cuentasService;

    public CuentasCajeroController(CuentasCajeroService cuentasService) {
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

}
