package com.duoc.ms_cuentas.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.ms_cuentas.exception.ErrorResponse;
import com.duoc.ms_cuentas.model.Cuenta;
import com.duoc.ms_cuentas.service.CuentaService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping("/api/banco/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(cuentaService.findAll());
    }


    @GetMapping("/{id}")
    @CircuitBreaker(name = "bancoBreaker", fallbackMethod ="findCuentaFallback")
    public ResponseEntity<?> findCuenta(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.findCuenta(id);
        if (cuenta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(cuenta);
    }

    // Método fallback para búsqueda de estdo de cuenta por ID
    // Retorna un objeto ErrorResponse informando la no disponibilidad del servicio de banco
    public ResponseEntity<ErrorResponse> findCuentaFallback(Long id, Throwable cause) {
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.SERVICE_UNAVAILABLE.value(),
            "Banco no disponible",
            "No fue posible consultar los datos de la cuenta en este momento",
            "/api/banco/cuenta/" + id
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    @PostMapping
    public ResponseEntity<?> saveCuenta(@Valid @RequestBody Cuenta cuenta){
        cuenta.setId(null);
        return ResponseEntity.ok(cuentaService.save(cuenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCuenta(
        @PathVariable  Long id, 
        @Valid @RequestBody Cuenta cuenta
    ) {
        if (cuentaService.findCuenta(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        cuenta.setId(id);
        return ResponseEntity.ok(cuentaService.save(cuenta));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id) {
        if (cuentaService.findCuenta(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        cuentaService.deleteCuenta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
