package com.naye.escuela.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record DatosCurso(
        @Schema(description = "Nombre del curso", example = "Programacion")
        String nombre,
        @Schema(description = "Descripcion del curso", example = "Fundamentos de Programacion")
        String descripcion,
        @Schema(description = "Creditos del curso", example = "5")
        Integer creditos
) {
}
