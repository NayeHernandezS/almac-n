package com.naye.escuela.services.alumnos;

import com.naye.escuela.dto.alumnos.AlumnoRequest;
import com.naye.escuela.dto.alumnos.AlumnoResponse;
import com.naye.escuela.entities.Alumno;
import com.naye.escuela.exceptions.EntidadRelacionExcepcion;
import com.naye.escuela.mappers.alumno.AlumnoMapper;
import com.naye.escuela.repositories.AlumnoRepository;
import com.naye.escuela.repositories.InscripcionesRepository;
import com.naye.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceAlumnoImpl implements ServiceAlumno {

    private final AlumnoRepository alumnoRepositorio;
    private final InscripcionesRepository inscripcionesRepository;
    private final AlumnoMapper alumnoMapper;


    @Transactional(readOnly = true)
    @Override
    public List<AlumnoResponse> listar() {
        log.info("Iniciando lista de alumnos");
        return alumnoRepositorio.findAll().stream()
                .map(alumnoMapper::entidadResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public AlumnoResponse obtenerPorId(Long id) {
        log.info("Iniciando obtenerPorId");
        return alumnoMapper.entidadResponse(obtenerAlumnoPorId(id));
    }

    @Override
    public AlumnoResponse registrar(AlumnoRequest request) {

        log.info("Iniciando el registro de alumnos");

        Alumno alumno = alumnoMapper.requestEntidad(
                request,
                generarEmail(request),
                generarMatricula(request)
        );
        alumnoRepositorio.save(alumno);
        return alumnoMapper.entidadResponse(alumno);
    }

    @Override
    @Transactional
    public AlumnoResponse actualizar(AlumnoRequest request, Long id) {
        Alumno alumno = obtenerAlumnoPorId(id);
            log.info("Actualizando alumno: " + alumno);
        if(alumno.cambioDatos(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim()
        )){
            alumno.actualizar(
                    request.nombre(),
                    request.apellidoPaterno(),
                    request.apellidoMaterno(),
                    generarEmail(request),
                    generarMatricula(request)
            );
        }

        return alumnoMapper.entidadResponse(alumno);
    }



    @Override
    public void eliminar(Long id) {

        log.info("Iniciando eliminacion de horarios");
        Alumno alumno = obtenerAlumnoPorId(id);
        alumnoRepositorio.delete(alumno);
        if (inscripcionesRepository.existsById(id))
            throw new EntidadRelacionExcepcion("No se puede eliminar al mestro ya quetiene grupos asignados");
        alumnoRepositorio.delete(alumno);
    }



    private Alumno obtenerAlumnoPorId(Long id){
        return ServiceUtils.obtenerEntidadOExcepetion(alumnoRepositorio, id, Alumno.class);
    }

    private String generarEmail(AlumnoRequest request){
        return alumnoRepositorio.generarEmail(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim()
        );

    }

    private String generarMatricula(AlumnoRequest request){
        return alumnoRepositorio.generarMatricula(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim()
        );
    }
}
