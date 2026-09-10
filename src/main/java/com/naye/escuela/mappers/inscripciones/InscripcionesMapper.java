package com.naye.escuela.mappers.inscripciones;

import com.naye.escuela.dto.inscripciones.IncripcionesRequest;
import com.naye.escuela.dto.inscripciones.IncripcionesResponse;
import com.naye.escuela.entities.Alumno;
import com.naye.escuela.entities.Grupo;
import com.naye.escuela.entities.Inscripciones;
import com.naye.escuela.mappers.CommonMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InscripcionesMapper implements CommonMapper<IncripcionesRequest, IncripcionesResponse, Inscripciones> {

    @Override
    public Inscripciones requestEntidad(IncripcionesRequest request) {
        throw new UnsupportedOperationException("Use requestEntidad(request, alumno, grupo)");
    }

    public Inscripciones requestEntidad(IncripcionesRequest request, Alumno alumno, Grupo grupo) {
        if (request == null) return null;

        return Inscripciones.builder()
                .alumno(alumno)
                .grupo(grupo)
                .fechaIncripcion(LocalDate.now())
                .build();
    }

    @Override
    public IncripcionesResponse entidadResponse(Inscripciones entidad) {
        if (entidad == null) return null;

        String nombreAlumno = entidad.getAlumno() == null
                ? null
                : String.join(" ",
                entidad.getAlumno().getNombre(),
                entidad.getAlumno().getAPaterno(),
                entidad.getAlumno().getAMaterno()).trim();

        String nombreGrupo = entidad.getGrupo() == null || entidad.getGrupo().getCurso() == null
                ? null
                : entidad.getGrupo().getCurso().getNombre();

        return new IncripcionesResponse(
                entidad.getId(),
                nombreAlumno,
                nombreGrupo,
                entidad.getFechaIncripcion(),
                entidad.getCalificacion() != null ? entidad.getCalificacion().getCalificacion() : null
        );
    }
}
