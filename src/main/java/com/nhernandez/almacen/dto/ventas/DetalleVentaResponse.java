package com.nhernandez.almacen.dto.ventas;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record DetalleVentaResponse(

        @Schema(description = "ID del producto", example = "1")
        Long idProducto,

        @Schema(description = "Nombre del producto", example = "Laptop gamer")
        String nombreProducto,

        @Schema(description = "Cantidad de unidades vendidas", example = "10")
        Integer cantidadProducto,

        @Schema(description = "Precio unitario del producto", example = "1500.00")
        BigDecimal precioProdcuto,

        @Schema(description = "Subtotal del producto", example = "1500.00")
        BigDecimal subTotal
) {

}
