package com.naye.escuela.mappers.horarios;

import com.naye.escuela.dto.horarios.RequestHorarios;
import com.naye.escuela.dto.horarios.ResponseHorarios;
import com.naye.escuela.entities.Grupo;
import com.naye.escuela.entities.Horarios;
import com.naye.escuela.mappers.CommonMapper;
import org.springframework.stereotype.Component;

@Component
public class HorariosMappers implements CommonMapper<RequestHorarios, ResponseHorarios, Horarios> {

    @Override
    public Horarios requestEntidad(RequestHorarios request) {
        throw new UnsupportedOperationException("Use requestEntidad(request, grupo)");
    }

    public Horarios requestEntidad(RequestHorarios request, Grupo grupo) {
        if (request == null) return null;

        return Horarios.builder()
                .grupo(grupo)
                .diaSemana(request.diaSemana())
                .horaInicio(request.horaInicio().trim())
                .horaFin(request.horaFin().trim())
                .build();
    }

    @Override
    public ResponseHorarios entidadResponse(Horarios entidad) {
        if (entidad == null) return null;

        return new ResponseHorarios(
                entidad.getId(),
                entidad.getGrupo().getId(),
                entidad.getDiaSemana(),
                entidad.getHoraInicio(),
                entidad.getHoraFin()
        );
    }
}
