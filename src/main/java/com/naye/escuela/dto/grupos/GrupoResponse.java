package com.naye.escuela.dto.grupos;

import io.swagger.v3.oas.annotations.media.Schema;

public record GrupoResponse(

        @Schema(description = "Id del grupo", example = "1")
        Long id,

        @Schema(description = "Nombre del curso")
        String curso,

        @Schema(description = "Nombre del maestro")
        String maestro,

        @Schema(description = "Nombre del aula")
        String aula,

        @Schema(description = "Periodo escolar", example = "2026-1")
        String periodo
) {
}
