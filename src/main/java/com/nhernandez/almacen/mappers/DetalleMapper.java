package com.nhernandez.almacen.mappers;

import com.nhernandez.almacen.dto.ventas.DetalleVentaRequest;
import com.nhernandez.almacen.entities.DetalleVenta;
import org.springframework.stereotype.Component;

@Component
public class DetalleMapper {
    public DetalleVentaRequest detalleVentaR(DetalleVenta detalleVenta){
        if (detalleVenta == null){
            return null;
        }
       return DetalleVentaRequest.builder()
               .idProducto(detalleVenta.getProducto()
                       .getId())
               .build();
    }
}
