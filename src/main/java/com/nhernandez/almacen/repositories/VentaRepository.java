package com.nhernandez.almacen.repositories;

import com.nhernandez.almacen.entities.Venta;
import com.nhernandez.almacen.enums.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByEstadoVenta(EstadoVenta estadoVenta);
}
