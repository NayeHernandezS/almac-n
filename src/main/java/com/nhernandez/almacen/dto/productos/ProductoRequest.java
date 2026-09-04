package com.nhernandez.almacen.dto.productos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Datos necesarios para crear o actualizar un producto")
public record ProductoRequest(

        @Schema(description = "NoMBRE DEL PRODUCTO", example = "laptop gamer")
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 5, max = 30, message = "El nombre debe tener entre 5 y 30 caracteres")
        String nombre,

        @Schema(description = "CATEGORIA DEL PRODUCTO", example = "Electronica")
        @NotNull(message = "La categoria es requerida")
        String categoria,

        @Schema(description = "PRECIO DEL PRODUCTO", example = "15000.99")
        @NotNull(message = "El precio es requerido")
        @Positive(message = "La cantida debe ser mayo a 0")
        BigDecimal precio,

        @Schema(description = "CANTIDAD DISPONIBLE DEL PRODUCTO", example = "300")
        @NotNull(message = "La cantidad es requerida")
        @Positive(message = "La cantidad debe ser mayor a o")
        Integer cantidad) {

}
