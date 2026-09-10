package com.naye.escuela.dto.alumnos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record AlumnoRequest(

        Long id,

        @NotBlank(message = "El nombre es requerido")
        @Schema(description = "Nombre del Alumno", example = "Nayely")
        @Size(min = 1, max = 50)
        String nombre,

        @NotBlank(message = "El apellido paterno es requerido")
        @Schema(description = "Apellidp paterno", example = "Hernandez")
        @Size(min = 1, max = 50)
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno es requerido")
        @Schema(description = "Apellido materno", example = "Silva")
        @Size(min = 1, max = 50)
        String apellidoMaterno
) {
}
