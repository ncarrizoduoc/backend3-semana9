package com.duoc.bff_banco_web.model;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class Cuenta implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Positive(message = "El ID de cuenta debe ser un numero positivo")
    @NotNull(message = "Debe ingresar un ID de cuenta")
    private Long cuentaId;

    @NotBlank(message = "El nombre del titular no puede ser un texto en blanco")
    @NotNull(message = "Debe ingresar el nombre del titular de la cuenta")
    private String nombre;

    @NotNull(message = "Debe ingresar el saldo inicial")
    @PositiveOrZero(message = "El saldo inicial debe ser mayor o igual a 0")
    private Integer saldoInicial;

    @NotNull(message = "Debe ingresar el saldo final")
    @PositiveOrZero(message = "El saldo final debe ser mayor o igual a 0")
    private Integer saldoFinal;

    @NotNull(message = "Debe ingresar La edad del titular")
    @PositiveOrZero(message = "La edad del titular debe ser mayor o igual a 0")
    private Integer edad;

    @NotNull(message = "Debe ingresar un tipo de cuenta")
    @NotBlank(message = "El tipo de cuenta no puede ser un texto en blanco")
    private String tipo;

    @PositiveOrZero(message = "La tasa de interes debe ser mayor o igual a 0")
    private Double tasaInteres;

}
