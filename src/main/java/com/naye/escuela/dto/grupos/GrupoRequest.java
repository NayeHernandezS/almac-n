package com.naye.escuela.dto.grupos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record GrupoRequest(

        @NotNull(message = "El curso es requerido")
        @Positive(message = "El id del curso debe ser positivo")
        @Schema(description = "Id del curso", example = "1")
        Long idCurso,

        @NotNull(message = "El maestro es requerido")
        @Positive(message = "El id del maestro debe ser positivo")
        @Schema(description = "Id del maestro", example = "1")
        Long idMaestro,

        @NotNull(message = "El aula es requerida")
        @Positive(message = "El id del aula debe ser positivo")
        @Schema(description = "Id del aula", example = "1")
        Long idAula,

        @NotBlank(message = "El periodo es requerido")
        @Size(min = 1, max = 20, message = "El periodo debe tener entre 1 y 20 caracteres")
        @Schema(description = "Periodo escolar", example = "2026-1")
        String periodo
) {
}
