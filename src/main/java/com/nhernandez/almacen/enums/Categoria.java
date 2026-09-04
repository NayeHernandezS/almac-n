package com.nhernandez.almacen.enums;

import com.nhernandez.almacen.exceptions.RecursoNoEncontradoException;
import com.nhernandez.almacen.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Categoria {

    ALIMENTO("Alimento"),
    HIGIENE("Higiene"),
    JUGUETE("Juguete"),
    ELECTRONICA("Electronica"),
    ROPA("Ropa"),
    ACCESORIO("Accesorio"),
    FARMACIA("Farmacia");

    private  final String descripcion;

    public static Categoria obtenerCategoriaPorDescripcion(String descripcion){
        StringCustomUtils.validarNoVacio(descripcion, "La descropcion es requerida");

        String descripcionNornalizada = StringCustomUtils.quitaracentos(descripcion);

        for (Categoria categoria: values()){
            if (StringCustomUtils.quitaracentos(categoria.descripcion).equalsIgnoreCase(descripcionNornalizada))
                return  categoria;
        }
        throw  new RecursoNoEncontradoException("No existe la categoria con la descripcion "+ descripcion);

    }
}
