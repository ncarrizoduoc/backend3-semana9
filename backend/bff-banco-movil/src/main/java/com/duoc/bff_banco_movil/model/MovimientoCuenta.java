package com.duoc.bff_banco_movil.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MovimientoCuenta {
    
    private Long id;
    private Long cuentaId;
    private LocalDate fecha;
    private String transaccion;
    private Integer monto;
    private String descripcion;

}
