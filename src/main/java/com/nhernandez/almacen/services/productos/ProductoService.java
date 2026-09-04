package com.nhernandez.almacen.services.productos;

import com.nhernandez.almacen.dto.productos.ProductoRequest;
import com.nhernandez.almacen.dto.productos.ProductosResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService {

    List<ProductosResponse> listar(
            String nombre, String categoria,
            BigDecimal precioMin, BigDecimal precioMax
    );

    ProductosResponse obtenerPorId(Long id);
    ProductosResponse registrar(ProductoRequest request);
    ProductosResponse actualizar(ProductoRequest request, Long id);

    void eliminar(Long id);
}
