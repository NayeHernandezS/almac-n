package com.nhernandez.almacen.mappers;

import com.nhernandez.almacen.dto.productos.ProductoRequest;
import com.nhernandez.almacen.dto.productos.ProductosResponse;
import com.nhernandez.almacen.entities.Producto;
import com.nhernandez.almacen.enums.Categoria;
import org.springframework.stereotype.Component;

@Component //gestiona su ciclo de vida
public class ProductoMapper {

    public Producto requestAEntidad(ProductoRequest request, Categoria categoria){
        if (request == null) return null;
            return Producto.builder()
                    .nombre(request.nombre().trim())
                    .categoria(null)
                    .precio(request.precio())
                    .cantidad(request.cantidad())
                    .build();
    }

    public ProductosResponse entidadAResponse(Producto producto){
        if (producto == null) return null;

        return new ProductosResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria().getDescripcion(),
                producto.getPrecio(),
                producto.getCantidad()

        );
    }
}
