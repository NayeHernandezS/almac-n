package com.nhernandez.almacen.controllers;

import com.nhernandez.almacen.dto.sucursales.SucursalesRequest;
import com.nhernandez.almacen.dto.sucursales.SucursalesResponse;
import com.nhernandez.almacen.services.sucursales.SucursalesService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@AllArgsConstructor
@Validated
public class SucursalController {

    private SucursalesService sucursalesService;



    @GetMapping
    @Operation(
            summary = "Listar productos",
            tags = {"Sucursales - Consultas"}
    )

    public ResponseEntity<List<SucursalesResponse>> listar() {
        return ResponseEntity.ok(sucursalesService.listar());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Listar Obtener por ID",
            tags = {"Productos - Consultas"}
    )

    public ResponseEntity<SucursalesResponse> obtenerPorId(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id
    ) {
        return ResponseEntity.ok(sucursalesService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(
            summary = "Registrar nuevo producto",
            tags = {"Productos - Gestión"}
    )

    public ResponseEntity<SucursalesResponse> registrar(
            @Valid @RequestBody SucursalesRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalesService.crearSucursal(request));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar producto",
            tags = {"Productos - Gestión"}
    )

    public ResponseEntity<SucursalesResponse> actualizar(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id,
            @Valid @RequestBody SucursalesRequest request
    ) {
        return ResponseEntity.ok(sucursalesService.actualizarSucursal(request, id));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un producto",
            tags = {"Productos - Gestión"}
    )

    public ResponseEntity<Void> eliminar(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id
    ) {
        sucursalesService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
