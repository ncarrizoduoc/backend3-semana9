package com.duoc.banco.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateParser {

    // Formatos de fecha válidos en el archivo
    private static final DateTimeFormatter[] FORMATOS = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("yyyy/MM/dd")
    };

    // Convertir String a fecha
    public static LocalDate toFecha(String fechaStr) {
        // Si la fila del CSV no incluye fecha, retorna null
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return null;
        }

        // Se intenta convertir el String a fecha usando todos los formatos validos
        String fechaLimpia = fechaStr.trim();
        for (DateTimeFormatter formatter : FORMATOS) {
            try {
                return LocalDate.parse(fechaLimpia, formatter);
            } catch (DateTimeParseException e) {
                // Ignorar y probar el siguiente formato
            }
        }
        // Si el String no corresponde a un formato valido, lanza una excepción (produce un Skip)
        throw new DateTimeParseException("No se pudo parsear la fecha: " + fechaStr, fechaStr, 0);
    }


}
