package com.duoc.bff_banco_movil.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.bff_banco_movil.model.EstadoCuenta;
import com.duoc.bff_banco_movil.model.EstadoCuentaResponse;
import com.duoc.bff_banco_movil.model.MovimientoCuenta;
import com.duoc.bff_banco_movil.model.MovimientoCuentaResponse;
import com.duoc.bff_banco_movil.model.TransferenciaRequest;
import com.duoc.bff_banco_movil.service.BancoMovilService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController 
@RequestMapping("/api/banco/movil")
public class BancoMovilController {

    private final BancoMovilService bancoMovilService;

    public BancoMovilController(BancoMovilService bancoMovilService) {
        this.bancoMovilService = bancoMovilService;
    }

    @GetMapping("/estado-cuenta/{id}")
    public ResponseEntity<EstadoCuentaResponse> getEstadoCuenta(
        @PathVariable Long id,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ) {
        EstadoCuenta estadoCuenta = bancoMovilService.getEstadoCuenta(id, jwt);
        if (estadoCuenta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new EstadoCuentaResponse(estadoCuenta));
        
    }

    @PostMapping("/transferencias")
    public ResponseEntity<MovimientoCuentaResponse> realizarTransferencia(
        @Valid @RequestBody TransferenciaRequest request,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ) {
        MovimientoCuenta movimiento = bancoMovilService.realizarTransferencia(request, jwt);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MovimientoCuentaResponse(movimiento));
    }

}
