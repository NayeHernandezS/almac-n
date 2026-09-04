package com.nhernandez.almacen.services.productos;

import com.nhernandez.almacen.dto.productos.ProductoRequest;
import com.nhernandez.almacen.dto.productos.ProductosResponse;
import com.nhernandez.almacen.entities.Producto;
import com.nhernandez.almacen.enums.Categoria;
import com.nhernandez.almacen.exceptions.RecursoNoEncontradoException;
import com.nhernandez.almacen.mappers.ProductoMapper;
import com.nhernandez.almacen.repositories.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.*;

@Service
@AllArgsConstructor
@Transactional
@Slf4j // para usar logger
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional(readOnly = true)//indica que solo son de lectura
    public List<ProductosResponse> listar(String nombre, String categoria, BigDecimal precioMin, BigDecimal precioMax) {

        log.info("Listando los productos");

        Specification<Producto> specification = ((root, query, criteriaBuilder) -> {
            List<Predicate> filtrados = new ArrayList<>();

            //1Nombre:
            if (nombre != null && !nombre.isBlank()) {
                filtrados.add(criteriaBuilder.like(criteriaBuilder
                                .lower(root.get("nombre")),
                        "%" + nombre.toLowerCase().trim() + "%"
                ));
            }
            // 2. Categoría: exacta
            if (categoria != null && !categoria.isBlank()) {
                try {
                    Categoria categoriaEnum = Categoria.obtenerCategoriaPorDescripcion(categoria.trim());
                    filtrados.add(criteriaBuilder.equal(root.get("categoria"),categoriaEnum));
                }catch (IllegalArgumentException e){
                    throw new IllegalArgumentException("Lo siento, la categoria no existe");
                }
            }

            // 3. Rango de precios: Mínimo
            if (precioMin != null) {
                filtrados.add(criteriaBuilder.greaterThanOrEqualTo(root.<BigDecimal>get("precio"), precioMin));
            }

            // 4. Rango de precios: Máximo
            if (precioMax != null) {
                filtrados.add(criteriaBuilder.lessThanOrEqualTo(root.<BigDecimal>get("precio"), precioMax));
            }

            return criteriaBuilder.and(filtrados.toArray(new jakarta.persistence.criteria.Predicate[0]));
        });
        return productoRepository.findAll(specification)
                .stream()
                .map(productoMapper::entidadAResponse)
                .toList();

    }


           @Override
    public ProductosResponse obtenerPorId(Long id) {
        log.info("Mostrando los productos");
        return productoMapper.entidadAResponse(obtenerProductoException(id));
    }

    @Override
    public ProductosResponse registrar(ProductoRequest request) {
        Producto producto = productoMapper.requestAEntidad(
                request, Categoria.obtenerCategoriaPorDescripcion(request.categoria()));
        productoRepository.save(producto);

        log.info("Registrando los productos");
        return productoMapper.entidadAResponse(producto);
    }

    @Override
    public ProductosResponse actualizar(ProductoRequest request, Long id) {
       Producto producto = obtenerProductoException(id);

       producto.actualizar(
               request.nombre(),
               Categoria.obtenerCategoriaPorDescripcion(
                       request.categoria()),
               request.precio(),
               request.cantidad()
       );
       log.info("Actualizando los productos");
        return productoMapper.entidadAResponse(producto);
    }

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando prodducto de la base");
        Producto producto = obtenerProductoException(id);
        productoRepository.delete(producto);
    }

    private Producto obtenerProductoException(Long id){
        log.info("Buscando producto con id " + id);
        return productoRepository.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoException("El producto con id " + id + " no existe")
        );
    }
}
