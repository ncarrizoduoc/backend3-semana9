package com.duoc.bff_banco_cajero.model;

import lombok.Data;

@Data
public class MovimientoCuentaResponse {
    private Long cuentaId;
    private Integer monto;
    private String tipoTransaccion;

    public MovimientoCuentaResponse(MovimientoCuenta mov){
        cuentaId = mov.getCuentaId();
        monto = mov.getMonto();
        tipoTransaccion = mov.getTransaccion();
    }

}
