package com.nhernandez.almacen.mappers;

import com.nhernandez.almacen.dto.ventas.DetalleVentaRequest;
import com.nhernandez.almacen.dto.ventas.VentaResponse;
import com.nhernandez.almacen.entities.DetalleVenta;
import com.nhernandez.almacen.entities.Sucursal;
import com.nhernandez.almacen.entities.Venta;
import com.nhernandez.almacen.enums.EstadoVenta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class VentaMappers {

    //private final VentaMappers ventaMappers;
    private final DetalleMapper detalleMapper;


    public Venta requestAEntidad(EstadoVenta estadoVenta, LocalDate fecha, Sucursal sucursal, List<DetalleVenta> detalleVentas){

        return Venta.builder()
                .estadoVenta(estadoVenta)
                .fecha(fecha)
                .sucursal(sucursal)
                .detalleVentas(detalleVentas)
                .build();


    }

    public VentaResponse entidadResponse(Venta venta) {
        List<DetalleVentaRequest> detalleVentaRequests = null;

        if (venta.getDetalleVentas() != null){
            detalleVentaRequests = venta.getDetalleVentas()
                    .stream()
                    .map(detalleMapper::detalleVentaR).toList();
        }

          return VentaResponse.builder()
                  .id(venta.getId())
                  .fecha(venta.getFecha())
                  .estado(venta.getEstadoVenta() != null ? venta.getEstadoVenta().getDescripcion():null)
                  .build();

    }
}
