package com.duoc.bff_banco_cajero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class CuentaResponse {
    
    private Long id;
    private Long cuentaId;
    private String nombre;
    private Integer saldo;
    private String tipo;

    public CuentaResponse(Cuenta cuenta){
        this.id = cuenta.getId();
        this.cuentaId = cuenta.getCuentaId();
        this.saldo = cuenta.getSaldoFinal();
        this.tipo = cuenta.getTipo();
    }

}
