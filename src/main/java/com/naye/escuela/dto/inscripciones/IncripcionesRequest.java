package com.naye.escuela.dto.inscripciones;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IncripcionesRequest(

        @NotNull(message = "El alumno es requerido")
        @Positive(message = "El id del alumno debe ser positivo")
        @Schema(description = "Id del alumno", example = "1")
        Long idAlumno,

        @NotNull(message = "El grupo es requerido")
        @Positive(message = "El id del grupo debe ser positivo")
        @Schema(description = "Id del grupo", example = "1")
        Long idGrupo
) {
}
