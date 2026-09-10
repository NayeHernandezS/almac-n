package com.naye.escuela.dto.inscripciones;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IncripcionesResponse(

        @Schema(description = "Id de la inscripcion", example = "1")
        Long id,

        @Schema(description = "Nombre del alumno")
        String alumno,

        @Schema(description = "Curso del grupo")
        String grupo,

        @Schema(description = "Fecha de inscripcion")
        LocalDate fechaInscripcion,

        @Schema(description = "Calificacion del alumno", example = "9.0")
        BigDecimal calificacion
) {
}
