package com.nhernandez.almacen.dto.ventas;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record VentaRequest(
        @Schema(description = "ID de la venta o ticket", example = "1")
        @NotBlank(message = "El id del venta es requerido")
        @Positive(message = "El id de la venta debe ser positivo")
        Long idVenta,
        @Schema(description = "")
        @NotEmpty(message = "")
        List<@Valid DetalleVentaRequest> venta

) {

}
