package com.nhernandez.almacen.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.nhernandez.almacen.exceptions.RecursoNoEncontradoException;
import com.nhernandez.almacen.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EstadoVenta {
    
    REGISTRADA(1L, "Registrada"),
    CANCELADA(0L, "Cancelada");
    
    private final Long codigo;

    @JsonValue
    private final String descripcion;

    public static EstadoVenta obtenerCategoriaPorDescripcion(String descripcion){
        StringCustomUtils.validarNoVacio(descripcion, "El codigo requerida");

        String descripcionNornalizada = StringCustomUtils.quitaracentos(descripcion);

        for (EstadoVenta estadoVenta: values()){
            if (estadoVenta.descripcion.equals(descripcion))
                return  estadoVenta;
        }
        throw  new RecursoNoEncontradoException("No existe un estado de venta con el codigo: " + descripcion);

    }
    
    public static EstadoVenta obtenerCategoriaPorCodigo(Long codigo){
        StringCustomUtils.validarNoVacio(String.valueOf(codigo), "El codigo requerida");

        String descripcionNornalizada = StringCustomUtils.quitaracentos(String.valueOf(codigo));

        for (EstadoVenta estadoVenta: values()){
            if (estadoVenta.codigo.equals(codigo))
                return  estadoVenta;
        }
        throw  new RecursoNoEncontradoException("No existe un estado de venta con el codigo: " + codigo);

    }
}
