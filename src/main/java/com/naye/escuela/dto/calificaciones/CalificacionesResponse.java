package com.naye.escuela.dto.calificaciones;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CalificacionesResponse(

        @Schema(description = "Id de la calificacion", example = "1")
        Long id,

        @Schema(description = "Calificacion", example = "9.5")
        BigDecimal calificacion,

        @Schema(description = "Fecha de registro")
        LocalDate fechaRegistro,

        @Schema(description = "Id de la inscripcion", example = "1")
        Long idInscripcion
) {
}
