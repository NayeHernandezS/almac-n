package com.naye.escuela.mappers;

import com.naye.escuela.dto.aula.AulaRequest;
import com.naye.escuela.dto.aula.AulaResponse;
import com.naye.escuela.entities.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper implements CommonMapper<AulaRequest, AulaResponse, Aula> {

    @Override
    public Aula requestEntidad(AulaRequest request) {
        if (request == null) return null;

        return Aula.builder()
                .nombre(request.nombre().trim())
                .capacidad(request.capacidad())
                .build();
    }

    @Override
    public AulaResponse entidadResponse(Aula entidad) {
        if (entidad == null) return null;

        return new AulaResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getCapacidad()
        );
    }
}
