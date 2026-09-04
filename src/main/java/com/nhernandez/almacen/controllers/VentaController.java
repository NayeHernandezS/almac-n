package com.nhernandez.almacen.controllers;

import com.nhernandez.almacen.dto.sucursales.SucursalesResponse;
import com.nhernandez.almacen.dto.ventas.VentaRequest;
import com.nhernandez.almacen.dto.ventas.VentaResponse;
import com.nhernandez.almacen.services.venta.VentaService;
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
@RequestMapping("/api/ventas")
@AllArgsConstructor
@Validated
public class VentaController {

    private VentaService ventaService;

    @GetMapping
    @Operation(
            summary = "Listar productos",
            tags = {"Ventas - Consultas"}
    )

    public ResponseEntity<List<VentaResponse>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }


    // 2. OBTENER VENTA POR ID (CORREGIDO: Se añade "/{id}" para romper la ambigüedad)
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener por ID",
            tags = {"Ventas - Consultas"} // Corregido de Sucursales a Ventas
    )
    public ResponseEntity<SucursalesResponse> obtenerPorId(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id
    ) {
        return ResponseEntity.ok(ventaService.obtenerPorIdActiva(id).sucursalesResponse());
    }


    // 3. REGISTRAR VENTA
    @PostMapping
    @Operation(
            summary = "Registrar nueva venta",
            tags = {"Ventas - Gestión"} // Corregido de Productos a Ventas
    )
    public ResponseEntity<VentaResponse> registrar(
            @Valid @RequestBody VentaRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.registrar(request));
    }


    // 4. ELIMINAR/CANCELAR VENTA (CORREGIDO: Se añade @DeleteMapping y la ruta)
    @PutMapping("/{id}/cancelar")
    @Operation(
            summary = "Cancelar venta por ID",
            tags = {"Ventas - Gestión"}
    )
    public ResponseEntity<VentaResponse> cancelar(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id
    ) {
        VentaResponse response = ventaService.cancelar(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/canceladas")
    @Operation(
            summary = "Consultar histórico de ventas canceladas",
            tags = {"Ventas - Consultas"}
    )
    public ResponseEntity<List<VentaResponse>> listarCanceladas() {
        return ResponseEntity.ok(ventaService.listarCanceladas());
    }
}
