package com.duoc.bff_banco_movil.model;

import java.util.List;

import lombok.Data;

@Data
public class EstadoCuenta {
    private Long cuentaId;
    private Integer ingresos;
    private Integer gastos;
    private Integer saldo;
    private List<MovimientoCuenta> movimientos;
}
