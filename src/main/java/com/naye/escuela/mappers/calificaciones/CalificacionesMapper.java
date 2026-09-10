package com.naye.escuela.mappers.calificaciones;

import com.naye.escuela.dto.calificaciones.CalificacionesRequest;
import com.naye.escuela.dto.calificaciones.CalificacionesResponse;
import com.naye.escuela.entities.Calificacion;
import com.naye.escuela.entities.Inscripciones;
import com.naye.escuela.mappers.CommonMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CalificacionesMapper implements CommonMapper<CalificacionesRequest, CalificacionesResponse, Calificacion> {

    @Override
    public Calificacion requestEntidad(CalificacionesRequest request) {
        throw new UnsupportedOperationException("Use requestEntidad(request, inscripcion)");
    }

    public Calificacion requestEntidad(CalificacionesRequest request, Inscripciones inscripcion) {
        if (request == null) return null;

        return Calificacion.builder()
                .calificacion(request.calificacion())
                .fechaRegistro(LocalDate.now())
                .inscripciones(inscripcion)
                .build();
    }

    @Override
    public CalificacionesResponse entidadResponse(Calificacion entidad) {
        if (entidad == null) return null;

        return new CalificacionesResponse(
                entidad.getId(),
                entidad.getCalificacion(),
                entidad.getFechaRegistro(),
                entidad.getInscripciones().getId()
        );
    }
}
