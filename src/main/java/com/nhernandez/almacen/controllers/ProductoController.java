package com.nhernandez.almacen.controllers;

import com.nhernandez.almacen.dto.productos.ProductoRequest;
import com.nhernandez.almacen.dto.productos.ProductosResponse;
import com.nhernandez.almacen.services.productos.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor
@Validated
@Tag(name = "Productos", description = "eNDO POINTS PARA LA GESTION DE PRODUCTOS")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    @Operation(
            summary = "Listar productos",
            tags = {"Productos - Consultas"}
    )

    public ResponseEntity<List<ProductosResponse>> listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(name = "preciomin", required = false) BigDecimal precioMin,
            @RequestParam(name = "preciomax", required = false) BigDecimal precioMax
    ) {
        return ResponseEntity.ok(productoService.listar(
                nombre, categoria, precioMin, precioMax));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener Producto con ID",
            tags = {"Productos - Consultas"}
    )

    public ResponseEntity<ProductosResponse> obtenerPorId(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id
    ) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(
            summary = "Registrar nuevo producto",
            tags = {"Productos - Gestión"}
    )

    public ResponseEntity<ProductosResponse> registrar(
            @Valid @RequestBody ProductoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.registrar(request));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar producto",
            tags = {"Productos - Gestión"}
    )

    public ResponseEntity<ProductosResponse> actualizar(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id,
            @Valid @RequestBody ProductoRequest request
    ) {
        return ResponseEntity.ok(productoService.actualizar(request, id));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un producto",
            tags = {"Productos - Gestión"}
    )

    public ResponseEntity<Void> eliminar(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id
    ) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
