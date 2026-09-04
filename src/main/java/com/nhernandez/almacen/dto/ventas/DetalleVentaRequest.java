package com.nhernandez.almacen.dto.ventas;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Valid
@Builder
public record DetalleVentaRequest(
        @Schema(description = "ID del producto", example = "1")
        @NotNull(message = "El id del producto es requerido")
        @Positive(message = "El id del producto debe ser positivo")
        Long idProducto,

        @Schema(description = "")
        @NotNull(message = "")
        @Positive(message = "")
        Integer cantidadProducto
) {
}
