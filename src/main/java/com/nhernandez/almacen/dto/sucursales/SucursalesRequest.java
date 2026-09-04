package com.nhernandez.almacen.dto.sucursales;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para crear o actualizar una sucursal")
public record SucursalesRequest(

        @Schema(description = "NoMBRE DE LA SUCURSAL", example = "SUC201PUEBLA")
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 5, max = 50, message = "El nombre debe tener entre 5 y 50 carqacteres")
        String nombre,


        @Schema(description = "DIRECCION DE LA SUCURSAL", example = "CALLE BENITO N8")
        @NotBlank(message = "La direccion es requerida")
        @Size(min = 10, max = 150, message = "La direccion debe tener entre 10 y 150 caracteres")
        String direccion
) {

}
