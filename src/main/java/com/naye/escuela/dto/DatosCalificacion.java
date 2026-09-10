package com.naye.escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record DatosCalificacion(
        @Schema(description = "Nombre del curso")
        String curso,
        @Schema(description = "Descripcion del curso")
        String periodo,
        @Schema(description = "Creditos del curso", example = "5")
        BigDecimal calificacion
) {
}
