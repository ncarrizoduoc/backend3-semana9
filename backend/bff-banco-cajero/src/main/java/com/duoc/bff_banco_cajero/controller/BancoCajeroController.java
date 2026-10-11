package com.duoc.bff_banco_cajero.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.bff_banco_cajero.model.EstadoCuenta;
import com.duoc.bff_banco_cajero.model.EstadoCuentaResponse;
import com.duoc.bff_banco_cajero.model.MovimientoCuentaRequest;
import com.duoc.bff_banco_cajero.model.MovimientoCuentaResponse;
import com.duoc.bff_banco_cajero.service.BancoCajeroService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController 
@RequestMapping("/api/banco/cajero")
public class BancoCajeroController {

    private final BancoCajeroService bancoCajeroService;

    public BancoCajeroController(BancoCajeroService bancoCajeroService) {
        this.bancoCajeroService = bancoCajeroService;
    }

    @GetMapping("/estado-cuenta/{id}")
    public ResponseEntity<EstadoCuentaResponse> getEstadoCuenta(
        @PathVariable Long id,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ) {
        EstadoCuenta estadoCuenta = bancoCajeroService.getEstadoCuenta(id, jwt);
        if (estadoCuenta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new EstadoCuentaResponse(estadoCuenta));
        
    }

    @PostMapping("/movimientos")
    public ResponseEntity<MovimientoCuentaResponse> realizarMovimiento(
        @Valid @RequestBody MovimientoCuentaRequest request,
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ) {
        MovimientoCuentaResponse movimiento = new MovimientoCuentaResponse(bancoCajeroService.realizarMovimiento(request, jwt));
        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }
    

}
