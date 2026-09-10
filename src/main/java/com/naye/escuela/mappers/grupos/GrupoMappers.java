package com.naye.escuela.mappers.grupos;

import com.naye.escuela.dto.grupos.GrupoRequest;
import com.naye.escuela.dto.grupos.GrupoResponse;
import com.naye.escuela.entities.Aula;
import com.naye.escuela.entities.Curso;
import com.naye.escuela.entities.Grupo;
import com.naye.escuela.entities.Maestro;
import com.naye.escuela.mappers.CommonMapper;
import org.springframework.stereotype.Component;

@Component
public class GrupoMappers implements CommonMapper<GrupoRequest, GrupoResponse, Grupo> {

    @Override
    public Grupo requestEntidad(GrupoRequest request) {
        throw new IllegalArgumentException("El grupo no puede ser encontrado");
    }

    public Grupo requestEntidad(GrupoRequest request, Curso curso, Maestro maestro, Aula aula) {
        if (request == null) return null;

        return Grupo.builder()
                .curso(curso)
                .maestro(maestro)
                .aula(aula)
                .periodo(request.periodo().trim())
                .build();
    }

    @Override
    public GrupoResponse entidadResponse(Grupo entidad) {
        if (entidad == null) return null;

        return new GrupoResponse(
                entidad.getId(),
                entidad.getCurso().getNombre(),
                String.join(" ",
                        entidad.getMaestro().getNombre(),
                        entidad.getMaestro().getApellidoPaterno(),
                        entidad.getMaestro().getApellidoMaterno()),
                entidad.getAula().getNombre(),
                entidad.getPeriodo()
        );
    }
}
