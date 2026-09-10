package com.naye.escuela.dto.maestros;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MaestroRequest(

        @NotBlank(message = "El nombre es requerido")
        @Schema(description = "Nombre del Maestro", example = "Ana")
        @Size(min = 1, max = 50, message = "El numero dene tener entre 1 y 50 caracteree")
        String nombre,

        @NotBlank(message = "El apellido paterno es requerido")
        @Schema(description = "Apellidp paterno", example = "Martinez")
        @Size(min = 1, max = 50, message = "El apellido Paterno debe tener entre 1 y 50 caracteres")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno es requerido")
        @Schema(description = "Apellido materno", example = "Reyes")
        @Size(min = 1, max = 50, message = "El apellido Materno debe tener entre 1 y 50 caracteres")
        String apellidoMaterno,

        @NotBlank(message = "El correo es requerido")
        @Schema(description = "Correo electronico", example = "reyesAna12@gmail.com")
        @Size(min = 1, max = 100, message = "El correo debe tener entre 1 y 100 caracteres")
        String email,

        @NotBlank(message = "El telefono es requerido")
        @Schema(description = "TELEFONO", example = "7352713050")
        @Size(min = 10, max = 10, message = "El telefono debe tener solo 10 digitos")
        @Pattern(regexp = "^[0-9]+$", message = "El telefono debe contener solo numeros")
        String telefono

) {
}
