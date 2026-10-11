package com.duoc.banco.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoCuenta {
    private Long cuentaId;
    private LocalDate fecha;
    private String transaccion;
    private Integer monto;
    private String descripcion;
    private boolean ultimoDelGrupo = false;

}
