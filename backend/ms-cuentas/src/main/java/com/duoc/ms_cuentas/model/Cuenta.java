package com.duoc.ms_cuentas.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "intereses")
@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class Cuenta implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cuenta_id", nullable = false)
    @Positive(message = "El ID de cuenta debe ser un numero positivo")
    @NotNull(message = "Debe ingresar un ID de cuenta")
    private Long cuentaId;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre del titular no puede ser un texto en blanco")
    @NotNull(message = "Debe ingresar el nombre del titular de la cuenta")
    private String nombre;

    @Column(name = "saldo_inicial", nullable = false)
    @NotNull(message = "Debe ingresar el saldo inicial")
    @PositiveOrZero(message = "El saldo inicial debe ser mayor o igual a 0")
    private Integer saldoInicial;

    @Column(name = "saldo_final", nullable = false)
    @NotNull(message = "Debe ingresar el saldo final")
    @PositiveOrZero(message = "El saldo final debe ser mayor o igual a 0")
    private Integer saldoFinal;

    @Column(name = "edad", nullable = false)
    @NotNull(message = "Debe ingresar La edad del titular")
    @PositiveOrZero(message = "La edad del titular debe ser mayor o igual a 0")
    private Integer edad;

    @Column(name = "tipo", nullable = false)
    @NotNull(message = "Debe ingresar un tipo de cuenta")
    @NotBlank(message = "El tipo de cuenta no puede ser un texto en blanco")
    private String tipo;

    @Column(name = "tasa_interes", nullable = false)
    @PositiveOrZero(message = "La tasa de interes debe ser mayor o igual a 0")
    private Double tasaInteres;

}
