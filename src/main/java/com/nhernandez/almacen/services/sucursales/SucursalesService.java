package com.nhernandez.almacen.services.sucursales;

import com.nhernandez.almacen.dto.sucursales.SucursalesRequest;
import com.nhernandez.almacen.dto.sucursales.SucursalesResponse;
import com.nhernandez.almacen.entities.Sucursal;

import java.util.List;

public interface SucursalesService {

    List<SucursalesResponse> listar();

    SucursalesResponse obtenerPorId(Long id);
    SucursalesResponse crearSucursal(SucursalesRequest request);
    SucursalesResponse actualizarSucursal(SucursalesRequest request, Long id);
    void eliminarSucursal(Long id);
}
