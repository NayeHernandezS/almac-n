package com.nhernandez.almacen.utils;

import com.nhernandez.almacen.entities.Producto;
import com.nhernandez.almacen.entities.Sucursal;
import com.nhernandez.almacen.entities.Venta;
import com.nhernandez.almacen.enums.Categoria;
import com.nhernandez.almacen.enums.EstadoVenta;
import com.nhernandez.almacen.repositories.ProductoRepository;
import com.nhernandez.almacen.repositories.SucursalesRepository;
import com.nhernandez.almacen.repositories.VentaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class DatosIniciales implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final SucursalesRepository sucursalesRepository;
    private final VentaRepository ventaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productoRepository.count() == 0){
            productoRepository.saveAll(List.of(
                    new Producto(null, "Laptop gamer", Categoria.ELECTRONICA, BigDecimal.valueOf(1500), 10),
                    new Producto(null, "muse", Categoria.ELECTRONICA, BigDecimal.valueOf(500), 10),
                    new Producto(null, "playera", Categoria.ROPA, BigDecimal.valueOf(300), 100)

                    ));

            log.info("Productos cargados correctamente");
        }

        if (sucursalesRepository.count() == 0){
            sucursalesRepository.saveAll(List.of(
                    new Sucursal(null, "SUC01PUE", "Puebla ejempl tr network"),
                    new Sucursal(null, "SUC01MOR", "Morelos, Cuautla"  ),
                    new Sucursal(null, "SUC01MEX", "CDMX Tlanepantla de baz")

            ));

            log.info("Sucursales cargados correctamente");

        }

        if (ventaRepository.count() == 0) {
            // 1. En lugar de crear una sucursal nueva, buscamos la que ya existe en tu tabla
            Sucursal sucursalExistente = sucursalesRepository.findAll().stream()
                    .findFirst()
                    .orElse(null);

            // 2. Si encontró la sucursal, le asignamos esa a las ventas de prueba
            if (sucursalExistente != null) {
                ventaRepository.saveAll(List.of(
                        Venta.builder().id(1L).estadoVenta(EstadoVenta.REGISTRADA).fecha(LocalDate.of(2026, 1, 20)).sucursal(sucursalExistente).detalleVentas(new ArrayList<>()).build(),
                        Venta.builder().id(2L).estadoVenta(EstadoVenta.CANCELADA).fecha(LocalDate.of(2016, 2, 18)).sucursal(sucursalExistente).detalleVentas(new ArrayList<>()).build(),
                        Venta.builder().id(3L).estadoVenta(EstadoVenta.REGISTRADA).fecha(LocalDate.of(2026, 3, 25)).sucursal(sucursalExistente).detalleVentas(new ArrayList<>()).build()
                ));

                log.info("¡Ventas cargadas correctamente!");
            } else {
                log.warn("No se pudieron cargar ventas porque no hay sucursales guardadas en la base de datos.");
            }
        }


    }
}
