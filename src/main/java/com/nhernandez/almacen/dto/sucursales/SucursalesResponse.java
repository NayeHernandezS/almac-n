package com.nhernandez.almacen.dto.sucursales;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion de una sucursal")
public record SucursalesResponse(

        @Schema(description = "Identificador de la sucursal", example = "1")
        Long id_sucursal,

                @Schema(description = "NOMBRE DE LA SUCURSAL", example = "SUC201PUEBLA")
                        String nombre_sucursal,

                @Schema(description = "DIRECCION DE LA SUCURSAL", example = "CALLE BENITO N8")
                        String direccion
) {}
