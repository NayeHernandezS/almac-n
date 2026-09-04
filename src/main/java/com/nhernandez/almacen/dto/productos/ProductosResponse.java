package com.nhernandez.almacen.dto.productos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductosResponse(

        Long id,
         @Schema(description = "nombre del producto", example = "Laptop")
        String nombre,
        @Schema(description = "categoria", example = "Electronica")
        String categoria,
        @Schema(description = "precio del producto", example = "10000")
        BigDecimal precio,
        @Schema(description = "cantidad del producto", example = "100")
        Integer cantidad) {


}
