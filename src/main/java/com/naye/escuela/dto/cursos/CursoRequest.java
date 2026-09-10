package com.naye.escuela.dto.cursos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record CursoRequest(

        @Schema(description = "Nombre del curso", example = "Programacion")
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres")
        String nombre,

        @Schema(description = "Descripcion del curso", example = "Fundamentos de programacion")
        @Size(max = 200, message = "La descripcion debe tener maximo 200 caracteres")
        String descripcion,

        @Schema(description = "Creditos del curso", example = "5")
        @NotNull(message = "Los creditos son requeridos")
        @Min(value = 1, message = "Los creditos minimos son 1")
        @Max(value = 10, message = "Los creditos maximos son 10")
        Integer creditos
) {
}
