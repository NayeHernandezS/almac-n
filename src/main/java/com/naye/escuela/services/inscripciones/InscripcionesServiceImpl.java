package com.naye.escuela.services.inscripciones;

import com.naye.escuela.dto.inscripciones.IncripcionesRequest;
import com.naye.escuela.dto.inscripciones.IncripcionesResponse;
import com.naye.escuela.entities.Alumno;
import com.naye.escuela.entities.Grupo;
import com.naye.escuela.entities.Inscripciones;
import com.naye.escuela.exceptions.EntidadRelacionExcepcion;
import com.naye.escuela.exceptions.RecursoNoEncontradoException;
import com.naye.escuela.mappers.inscripciones.InscripcionesMapper;
import com.naye.escuela.repositories.AlumnoRepository;
import com.naye.escuela.repositories.CalificacionesRepository;
import com.naye.escuela.repositories.GrupoRepository;
import com.naye.escuela.repositories.InscripcionesRepository;
import com.naye.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InscripcionesServiceImpl implements InscripcionesService {

    private final InscripcionesRepository inscripcionesRepository;
    private final InscripcionesMapper inscripcionesMapper;
    private final AlumnoRepository alumnoRepository;
    private final GrupoRepository grupoRepository;
    private final CalificacionesRepository calificacionesRepository;

    @Transactional(readOnly = true)
    @Override
    public List<IncripcionesResponse> listar() {
        log.info("Inscripciones listar");
        return inscripcionesRepository.findAllConRelaciones().stream()
                .map(inscripcionesMapper::entidadResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public IncripcionesResponse obtenerPorId(Long id) {
        log.info("Inscripciones obtenerPorId");
        return inscripcionesMapper.entidadResponse(obtenerIncripcionPorId(id));
    }

    @Transactional
    @Override
    public IncripcionesResponse registrar(IncripcionesRequest request) {

        log.info("Inscripciones registrar");
        Alumno alumno = obtenerAlumno(request.idAlumno());
        Grupo grupo = obtenerGrupo(request.idGrupo());

        Inscripciones inscripciones = inscripcionesMapper.requestEntidad(request, alumno, grupo);
        inscripcionesRepository.save(inscripciones);
        return inscripcionesMapper.entidadResponse(inscripciones);
    }

    @Transactional
    @Override
    public IncripcionesResponse actualizar(IncripcionesRequest request, Long id) {
        log.info("Inscripciones actualizar");
        Inscripciones inscripciones = obtenerIncripcionPorId(id);
        Alumno alumno = obtenerAlumno(request.idAlumno());
        Grupo grupo = obtenerGrupo(request.idGrupo());

        if (inscripciones.cambioDatos(alumno, grupo)) {
            inscripciones.actualizar(alumno, grupo);
        }
        return inscripcionesMapper.entidadResponse(inscripciones);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        log.info("Inscripciones eliminar");
        Inscripciones inscripciones = obtenerIncripcionPorId(id);
        if (calificacionesRepository.existsByInscripcionesId(id)) {
            throw new EntidadRelacionExcepcion("No se puede eliminar la inscripcion porque tiene calificacion asignada");
        }

        inscripcionesRepository.delete(inscripciones);
    }

    public Inscripciones obtenerIncripcionPorId(Long id) {
        return inscripcionesRepository.findByIdConRelaciones(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Inscripciones no encontrado con id " + id));
    }

    private Alumno obtenerAlumno(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(alumnoRepository, id, Alumno.class);
    }

    private Grupo obtenerGrupo(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(grupoRepository, id, Grupo.class);
    }
}
