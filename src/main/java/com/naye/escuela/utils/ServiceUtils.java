package com.naye.escuela.utils;

import com.naye.escuela.entities.Horarios;
import com.naye.escuela.exceptions.RecursoNoEncontradoException;
import com.naye.escuela.repositories.HorariosRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class ServiceUtils {

    public static <E, ID> E obtenerEntidadOExcepetion(
            JpaRepository<E, ID> repository,
            ID id, Class<E> clase
    ){
        String nombreEntidad = clase.getSimpleName();

        return repository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoException(nombreEntidad + "no encontrado con id " + id));

    }

}
