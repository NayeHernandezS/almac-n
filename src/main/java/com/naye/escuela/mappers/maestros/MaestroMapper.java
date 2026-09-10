package com.naye.escuela.mappers.maestros;

import com.naye.escuela.dto.DatosCurso;
import com.naye.escuela.dto.maestros.MaestroRequest;
import com.naye.escuela.dto.maestros.MaestroResponse;
import com.naye.escuela.entities.Grupo;
import com.naye.escuela.entities.Maestro;
import com.naye.escuela.mappers.CommonMapper;
import com.naye.escuela.mappers.curso.CursoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MaestroMapper implements CommonMapper<MaestroRequest, MaestroResponse, Maestro> {

    private final CursoMapper cursoMapper;

    public Maestro requestEntidad(MaestroRequest request){
        if (request == null) return null;
        return Maestro.builder()
                .nombre(request.nombre().trim())
                .apellidoPaterno(request.apellidoPaterno().trim())
                .apellidoMaterno(request.apellidoMaterno().trim())
                .email(request.email())
                .telefono(request.telefono().trim())
                .build();
    }

    public MaestroResponse entidadResponse(Maestro maestro){
        if(maestro == null) return null;

        List<DatosCurso> cursos = entidadDatosCurso(maestro);
        return new MaestroResponse(
                maestro.getId(),
                String.join(" ",
                        maestro.getNombre(),
                        maestro.getApellidoPaterno(),
                        maestro.getApellidoMaterno()),
                maestro.getEmail(),
                maestro.getTelefono(),
                cursos
        );
    }

    private  List<DatosCurso> entidadDatosCurso(Maestro maestro){
        if (maestro == null) return  List.of();
        return  maestro.getGrupos().stream()
        .map(Grupo::getCurso)
                .map(cursoMapper::entidadADatosCurso).toList();
    }
}
