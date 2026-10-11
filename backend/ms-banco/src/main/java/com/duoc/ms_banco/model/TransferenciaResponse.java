package com.duoc.ms_banco.model;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder 
public class TransferenciaResponse {
    private Long cuentaId;
    private Integer saldo;
    private MovimientoCuenta movimiento;

}
