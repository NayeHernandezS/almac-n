package com.nhernandez.almacen.mappers;

import com.nhernandez.almacen.dto.sucursales.SucursalesRequest;
import com.nhernandez.almacen.dto.sucursales.SucursalesResponse;
import com.nhernandez.almacen.entities.Sucursal;
import org.springframework.stereotype.Component;

@Component
public class SucursalesMapper {

    public Sucursal requestEntidadSucursal(SucursalesRequest request){
        if (request == null) return null;
        return Sucursal.builder().
                nombre(request.nombre().trim())
                .direccion(request.direccion())
                .build();
    }

    public SucursalesResponse entidadResponseSucursal(Sucursal sucursal){
        if (sucursal == null) return null;

       return new SucursalesResponse(
                sucursal.getId(),
                sucursal.getNombre(),
                sucursal.getDireccion());

    }
}
