package com.naye.escuela.dto.cursos;

import io.swagger.v3.oas.annotations.media.Schema;

public record CursoResponse(

        @Schema(description = "Id del curso", example = "1")
        Long id,

        @Schema(description = "Nombre del curso", example = "Programacion")
        String nombre,

        @Schema(description = "Descripcion del curso", example = "Fundamentos de programacion")
        String descripcion,

        @Schema(description = "Creditos del curso", example = "5")
        Integer creditos
) {
}
