package com.nhernandez.almacen.especificaciones;

import com.nhernandez.almacen.entities.Producto;
import com.nhernandez.almacen.enums.Categoria;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * Filtros dinámicos de productos usando Specifications de Spring Data JPA.
 *
 * Una Specification es un predicado (condición) que Hibernate traduce a SQL.
 * Así el filtrado ocurre en Oracle, no en memoria con Java.
 *
 * Truco importante: si un parámetro viene null o vacío, el método
 * devuelve null y Spring Data IGNORA ese filtro. El resto sí se aplica.
 */

public final class ProductoEspecificaciones {

    // Clase de utilidad: no sé instancia, solo se usan los métodos estáticos.
    private ProductoEspecificaciones() {
    }

    /**
     * Junta todos los filtros con AND.
     * Unrestricted() = "sin condiciones de inicio" (equivalente a SELECT * FROM productos).
     * Luego se van agregando solo los filtros que sí tienen valor.
     */
    public static Specification<Producto> conFiltros(
            String nombre,
            String categoria,
            BigDecimal precioMin,
            BigDecimal precioMax
    ) {
        return Specification.<Producto>unrestricted()
                .and(conNombre(nombre))
                .and(conCategoria(categoria))
                .and(conPrecioMin(precioMin))
                .and(conPrecioMax(precioMax));
    }

    /**
     * Coincidencia parcial e ignore case.
     * Ejemplo: nombre = "lap" encuentra "Laptop gamer".
     * En SQL queda algo como: LOWER(nombre) LIKE '%lap%'
     */
    public static Specification<Producto> conNombre(String nombre) {
        return (root, query, cb) -> {
            // root = la entidad Producto; cb = CriteriaBuilder (para armar LIKE, =, >=, etc.)
            if (nombre == null || nombre.isBlank()) {
                return null; // no agrega WHERE por nombre
            }
            return cb.like(
                    cb.lower(root.get("nombre")),
                    "%" + nombre.trim().toLowerCase() + "%"
            );
        };
    }

    /**
     * Igualdad exacta de categoría (no es LIKE).
     * La API recibe el texto ("Electronica") y lo convertimos al enum Categoria.ELECTRONICA.
     */
    public static Specification<Producto> conCategoria(String categoria) {
        return (root, query, cb) -> {
            if (categoria == null || categoria.isBlank()) {
                return null;
            }
            Categoria categoriaExacta = Categoria.obtenerCategoriaPorDescripcion(categoria.trim());
            return cb.equal(root.get("categoria"), categoriaExacta);
        };
    }

    /** Precio mayor o igual al mínimo. Si precioMin es null, no se filtra. */
    public static Specification<Producto> conPrecioMin(BigDecimal precioMin) {
        return (root, query, cb) -> {
            if (precioMin == null) {
                return null;
            }
            return cb.greaterThanOrEqualTo(root.get("precio"), precioMin);
        };
    }

    /** Precio menor o igual al máximo. Si precioMax es null, no se filtra. */
    public static Specification<Producto> conPrecioMax(BigDecimal precioMax) {
        return (root, query, cb) -> {
            if (precioMax == null) {
                return null;
            }
            return cb.lessThanOrEqualTo(root.get("precio"), precioMax);
        };
    }

}
