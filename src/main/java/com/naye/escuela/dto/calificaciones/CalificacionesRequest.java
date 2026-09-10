package com.naye.escuela.dto.calificaciones;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CalificacionesRequest(

        @NotNull(message = "La calificacion es requerida")
        @DecimalMin(value = "0.0", message = "La calificacion minima es 0")
        @DecimalMax(value = "10.0", message = "La calificacion maxima es 10")
        @Schema(description = "Calificacion", example = "9.5")
        BigDecimal calificacion,

        @NotNull(message = "La inscripcion es requerida")
        @Positive(message = "El id de la inscripcion debe ser positivo")
        @Schema(description = "Id de la inscripcion", example = "1")
        Long idInscripcion
) {
}
