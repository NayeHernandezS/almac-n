package com.naye.escuela.mappers.alumno;

import com.naye.escuela.dto.DatosCalificacion;
import com.naye.escuela.dto.alumnos.AlumnoRequest;
import com.naye.escuela.dto.alumnos.AlumnoResponse;
import com.naye.escuela.entities.Alumno;
import com.naye.escuela.mappers.CommonMapper;
import com.naye.escuela.utils.StringCustomUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AlumnoMapper implements CommonMapper<AlumnoRequest, AlumnoResponse, Alumno> {

    public Alumno requestEntidad(AlumnoRequest request){
        if (request == null) return null;
        return Alumno.builder()
                .nombre(request.nombre().trim())
                .aPaterno(request.apellidoPaterno().trim())
                .aMaterno(request.apellidoMaterno().trim())
                .fechaIngreso(new Date())
                .build();
    }

    public Alumno requestEntidad(AlumnoRequest request, String email, String matricula){
        if (request == null) return null;
        Alumno alumno = requestEntidad(request);
        alumno.asignarDatosAcademicos(email, matricula);
        return alumno;
    }

    public AlumnoResponse entidadResponse(Alumno alumno){
        if (alumno == null) return null;
        List<DatosCalificacion> calificaciones = entidadDatosCalificacion(alumno);
        return new AlumnoResponse(
                alumno.getId(),
                String.join(" ",
                        alumno.getNombre(),
                        alumno.getAPaterno(),
                        alumno.getAMaterno()),
                alumno.getEmail(),
                alumno.getMatricula(),
                StringCustomUtils.dateAString(alumno.getFechaIngreso()),
                        calificaciones,
                        alumno.calcularPromedio()
                );


    }
    private List<DatosCalificacion> entidadDatosCalificacion(Alumno entidad){
        if (entidad == null || entidad.getInscripciones() == null || entidad.getInscripciones().isEmpty())
            return List.of();

        return entidad.getInscripciones().stream()
                .map(inscripciones -> new DatosCalificacion(
                        inscripciones.getGrupo().getCurso().getNombre(),
                        inscripciones.getGrupo().getPeriodo(),
                        inscripciones.getCalificacion() != null
                                ? inscripciones.getCalificacion().getCalificacion()
                                : null
                )).toList();
    }
}
