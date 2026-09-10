package com.naye.escuela.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;

public record AulaResponse(

        @Schema(description = "Id del aula", example = "4")
        Long id,
    @Schema(description = "Nombre de la Aula", example = "101")
    String nombre,
    @Schema(description = "Capacidad del aula", example = "50")
    Integer capacidad
) {
}
