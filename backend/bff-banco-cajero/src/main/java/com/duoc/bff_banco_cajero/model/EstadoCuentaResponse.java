package com.duoc.bff_banco_cajero.model;

import lombok.Data;

@Data 
public class EstadoCuentaResponse {
    private Long cuentaId;
    private Integer saldo;

    public EstadoCuentaResponse(EstadoCuenta estadoCuenta) {
        this.cuentaId = estadoCuenta.getCuentaId();
        this.saldo = estadoCuenta.getSaldo();
    }

}
