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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController 
@RequestMapping("/api/banco/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService bancoService) {
        this.cuentaService = bancoService;
    }

    @GetMapping
    @CircuitBreaker(name = "bancoBreaker", fallbackMethod ="findCuentaFallback")
    public ResponseEntity<?> findCuenta(
            @RequestParam Long id, 
            @RequestParam String nombre, 
            @RequestParam String tipo
        ) {
        Cuenta cuenta = cuentaService.findCuenta(id, nombre, tipo);
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
            "/api/banco/cuenta"
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    @PostMapping
    public ResponseEntity<?> saveCuenta(@Valid @RequestBody Cuenta cuenta){
        cuenta.setCuentaId(null);
        return ResponseEntity.ok(cuentaService.save(cuenta));
    }

    @PutMapping
    public ResponseEntity<?> updateCuenta(
        @RequestParam Long id, 
        @RequestParam String nombre, 
        @RequestParam String tipo,
        @Valid @RequestBody Cuenta cuenta
    ) {
        if (cuentaService.findCuenta(id, nombre, tipo) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        cuentaService.deleteCuenta(id, nombre, tipo); // Se elimina la cuenta anterior
        cuenta.setCuentaId(id);
        return ResponseEntity.ok(cuentaService.save(cuenta)); // Se guarda la cuenta actualizada

    }

    @DeleteMapping
    public ResponseEntity<?> deleteCuenta(
        @RequestParam Long id, 
        @RequestParam String nombre, 
        @RequestParam String tipo
    ) {
        if (cuentaService.findCuenta(id, nombre, tipo) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        cuentaService.deleteCuenta(id, nombre, tipo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
