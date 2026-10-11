package com.duoc.bff_banco_movil.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MovimientoCuentaResponse {
    private LocalDate fecha;
    private Integer monto;
    private String transaccion;

    public MovimientoCuentaResponse(MovimientoCuenta mov){
        this.fecha = mov.getFecha();
        this.monto = mov.getMonto();
        this.transaccion = mov.getTransaccion();
    }
}
