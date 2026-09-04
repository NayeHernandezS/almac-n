package com.nhernandez.almacen.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Datos de los reportes de ventas")
public interface ReporteVentasSucursalResponse{

        Long sucursalId();
        String sucursalNombre();
        BigDecimal totalFacturado();
        Long cantidadProductosVendidos ();
}
