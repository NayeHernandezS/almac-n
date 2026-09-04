package com.nhernandez.almacen.repositories;

import com.nhernandez.almacen.dto.ReporteVentasSucursalResponse;
import com.nhernandez.almacen.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalesRepository extends JpaRepository<Sucursal, Long> {

    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);


    @Query(value =
            "SELECT s.id_sucursal AS sucursalId, " +
                    "       s.nombre AS sucursalNombre, " +
                    "       COALESCE(SUM(d.cantidad_producto * d.precio_producto), 0) AS totalFacturado, " +
                    "       COALESCE(SUM(d.cantidad_producto), 0) AS cantidadProductosVendidos " +
                    "FROM SUCURSALES s " +
                    "INNER JOIN VENTAS v ON s.id_sucursal = v.id_sucursal " +
                    "INNER JOIN DETALLES_VENTA d ON v.id_venta = d.id_venta " + // Cambia DETALLES_VENTA por tabla de detalles
                    "WHERE v.estado <> :estadoCancelado " +                     // Cambia 'estado' por tu columna (ej. ESTADO)
                    "GROUP BY s.id_sucursal, s.nombre",
            nativeQuery = true) // <--- ESTO EVITA LA VALIDACIÓN DE ENTIDADES EN EL ARRANQUE
    List<ReporteVentasSucursalResponse> obtenerReporteVentasPorSucursal(
            @Param("estadoCancelado") String estadoCancelado
    );
}
