package com.naye.escuela.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AulaRequest(

        @NotBlank(message = "El nombre es requerido")
        @Schema(description = "Nombre del aula", example = "201")
        @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
        String nombre,

        @NotNull(message = "La capacidad es requerida")
        @Schema(description = "Capacidad del aula", example = "50")
        @Min(value = 1, message = "La capacidad minima es 1")
        Integer capacidad
) {
}
