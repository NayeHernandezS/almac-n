package com.nhernandez.almacen.services.venta;


import com.nhernandez.almacen.dto.ventas.VentaRequest;
import com.nhernandez.almacen.dto.ventas.VentaResponse;
import com.nhernandez.almacen.entities.DetalleVenta;
import com.nhernandez.almacen.entities.Producto;
import com.nhernandez.almacen.entities.Sucursal;
import com.nhernandez.almacen.entities.Venta;
import com.nhernandez.almacen.enums.EstadoVenta;
import com.nhernandez.almacen.exceptions.RecursoNoEncontradoException;
import com.nhernandez.almacen.mappers.VentaMappers;
import com.nhernandez.almacen.repositories.ProductoRepository;
import com.nhernandez.almacen.repositories.SucursalesRepository;
import com.nhernandez.almacen.repositories.VentaRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class VentaServiceImpl implements VentaService{

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository; // Inyectado para validar stock
    private final SucursalesRepository sucursalRepository; // Inyectado para asociar la sucursal
    private final VentaMappers ventaMappers;


    @Transactional(readOnly = true)
    @Override
    public List<VentaResponse> listar() {
        log.info("Listando ventas");

        return ventaRepository.findByEstadoVenta(EstadoVenta.REGISTRADA).stream()
                .map(ventaMappers::entidadResponse).toList();
    }

    @Override
    public VentaResponse obtenerPorIdActiva(Long id) {
        log.info("Obteniendo la venta activa de el id: " + id);
        return ventaMappers.entidadResponse(obtenerPorIdException(id));
    }


    @Override
    public VentaResponse registrar(VentaRequest request) {
        log.info("Iniciando registro de venta con múltiples productos");

        // 1. Buscamos la sucursal (Usamos ID 1L por defecto si no viene en el request)
        Sucursal sucursal = sucursalRepository.findById(1L)
                .orElseThrow(() -> new RecursoNoEncontradoException("La sucursal por defecto no existe"));

        // 2. Creamos el cascarón de la venta usando el mapper
        Venta venta = ventaMappers.requestAEntidad(
                EstadoVenta.REGISTRADA,
                LocalDate.now(),
                sucursal,
                new ArrayList<>() // Inicializamos con una lista vacía mutable
        );

        // 3. Procesamos cada renglón que viene del request.venta()
        for (var detalleReq : request.venta()) {

            // Buscamos el producto en la base de datos
            Producto producto = productoRepository.findById(detalleReq.idProducto())
                    .orElseThrow(() -> new RecursoNoEncontradoException("El producto con ID " + detalleReq.idProducto() + " no existe"));

            // Descontamos el stock de manera segura (lanza excepción si no hay suficiente)
            producto.descontarrCantidad(detalleReq.cantidadProducto());

            // Construimos el DetalleVenta individual
            DetalleVenta detalleVenta = DetalleVenta.builder()
                    .producto(producto)
                    .cantidadProducto(detalleReq.cantidadProducto())
                    .precioProducto(producto.getPrecio()) // Usamos el Getter de Lombok
                    .build();


            venta.agregarDetalle(detalleVenta);

            // Guardamos el producto con su nuevo stock
            productoRepository.save(producto);
        }
        // 5. Guardamos la venta final (se guardarán los detalles en cascada)
        log.info("Guardando la venta completa en la base de datos de Oracle");
        Venta ventaGuardada = ventaRepository.save(venta);

        return ventaMappers.entidadResponse(ventaGuardada);
        }

    @Override
    public VentaResponse cancelar(Long id) {
        log.info("Cancelando la venta con ID: {}", id);
        Venta venta = obtenerPorIdException(id);

        // 2. Ejecutamos la lógica de negocio de tu Entidad (cambia a CANCELADA)
        venta.cancelar();

        if (venta.getDetalleVentas() != null) {
            for (DetalleVenta detalle : venta.getDetalleVentas()) {
                Producto producto = detalle.getProducto();
                int cantidadAVolver = detalle.getCantidadProducto();

                // Llamamos a tu método de la entidad Producto
                producto.aumentarCantidad(cantidadAVolver);
            }
        }

        // 3. Guardamos el estado actualizado en la base de datos
        Venta ventaActualizada = ventaRepository.save(venta);

        // 4. Mapeamos y retornamos la respuesta
        return ventaMappers.entidadResponse(ventaActualizada);
    }

    private Venta obtenerPorIdException(Long id){
        return ventaRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("La venta con id: " + id +"No existe!"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<VentaResponse> listarCanceladas() {
        log.info("Consultando el histórico de ventas CANCELADAS");

        return ventaRepository.findByEstadoVenta(EstadoVenta.CANCELADA).stream()
                .map(ventaMappers::entidadResponse)
                .toList();
    }

}
