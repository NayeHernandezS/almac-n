package com.naye.escuela.mappers.curso;

import com.naye.escuela.dto.DatosCurso;
import com.naye.escuela.dto.cursos.CursoRequest;
import com.naye.escuela.dto.cursos.CursoResponse;
import com.naye.escuela.entities.Curso;
import com.naye.escuela.mappers.CommonMapper;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper implements CommonMapper<CursoRequest, CursoResponse, Curso> {


    public DatosCurso entidadADatosCurso(Curso entidad) {
        if(entidad == null) return null;

        return new DatosCurso(
                entidad.getNombre(),
                entidad.getDescripcion() != null ? entidad.getDescripcion() : "Sin descripcion ",
                entidad.getCreditos());
    }

    @Override
    public Curso requestEntidad(CursoRequest request) {
        if(request == null) return null;

        return Curso.builder()
                .nombre(request.nombre().trim())
                .descripcion(request.descripcion())
                .creditos(request.creditos())
                .build();
    }

    @Override
    public CursoResponse entidadResponse(Curso entidad) {
        if(entidad == null) return null;

        return new CursoResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDescripcion(),
                entidad.getCreditos()
        );
    }
}

