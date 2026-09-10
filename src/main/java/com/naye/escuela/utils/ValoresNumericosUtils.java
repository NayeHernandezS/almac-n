package com.naye.escuela.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValoresNumericosUtils {

    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyy");

    public static <N extends Number> void validarNumeroRequerido(N numero){
        if (numero == null){
            throw  new IllegalArgumentException("El valor numeroco es requerido");
        }
    }

    public static void  validarEnteroPositivo(Integer entero, String mensaje){
        validarNumeroRequerido(entero);

        if (entero < 0){
            throw new IllegalArgumentException(mensaje);
        }
    }
    public static void validarBigDecimalPositivo(BigDecimal numero, String mensaje){
        validarNumeroRequerido(numero);

        if (numero.compareTo(BigDecimal.ZERO) < 0) {
            throw  new IllegalArgumentException(mensaje);
        }
    }
    public static LocalDate localDateString(LocalDate fecha){
        return fecha = Boolean.parseBoolean(null) ? null : LocalDate.parse(fecha.format(formato));
    }
}
