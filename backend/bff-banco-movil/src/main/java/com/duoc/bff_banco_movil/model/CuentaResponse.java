package com.duoc.bff_banco_movil.model;

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
    private Double tasaInteres;

    public CuentaResponse(Cuenta cuenta){
        this.id = cuenta.getId();
        this.cuentaId = cuenta.getCuentaId();
        this.nombre = cuenta.getNombre();
        this.saldo = cuenta.getSaldoFinal();
        this.tipo = cuenta.getTipo();
        this.tasaInteres = cuenta.getTasaInteres();
    }

}
