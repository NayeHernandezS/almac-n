package com.nhernandez.almacen.dto.ventas;

import com.nhernandez.almacen.dto.sucursales.SucursalesResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;

@Builder
@Schema(description = "Datos de una venta")
public record VentaResponse(

        @Schema(description = "", example = "")
        Long id,

        @Schema(description = "", example = "")
        LocalDate fecha,

        @Schema(description = "", example = "")
        String estado,

        @Schema(description = "", example = "")
        SucursalesResponse sucursalesResponse,

        @Schema(description = "", example = "")
        List<DetalleVentaResponse> detalles,

        @Schema(description = "", example = "")
        BigDecimal total
) {

}
