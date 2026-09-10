package com.naye.escuela.services.calificaciones;

import com.naye.escuela.dto.calificaciones.CalificacionesRequest;
import com.naye.escuela.dto.calificaciones.CalificacionesResponse;
import com.naye.escuela.entities.Calificacion;
import com.naye.escuela.entities.Inscripciones;
import com.naye.escuela.mappers.calificaciones.CalificacionesMapper;
import com.naye.escuela.repositories.CalificacionesRepository;
import com.naye.escuela.repositories.InscripcionesRepository;
import com.naye.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CalificacionesServiceIml implements CalificacionesService {

    private final CalificacionesRepository calificacionesRepository;
    private final CalificacionesMapper calificacionesMapper;
    private final InscripcionesRepository inscripcionesRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CalificacionesResponse> listar() {
        log.info("Inscripciones listar");
        return calificacionesRepository.findAll().stream()
                .map(calificacionesMapper::entidadResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CalificacionesResponse obtenerPorId(Long id) {
        log.info("Inscripciones obtenerPorId");
        return calificacionesMapper.entidadResponse(obtenerCalificacionPorId(id));
    }

    @Transactional
    @Override
    public CalificacionesResponse registrar(CalificacionesRequest request) {
        log.info("Inscripciones registrar");

        Inscripciones inscripcion = obtenerInscripcion(request.idInscripcion());
        Calificacion calificacion = calificacionesMapper.requestEntidad(request, inscripcion);
        calificacionesRepository.save(calificacion);
        return calificacionesMapper.entidadResponse(calificacion);
    }

    @Transactional
    @Override
    public CalificacionesResponse actualizar(CalificacionesRequest request, Long id) {
        log.info("Inscripciones actualizar");
        Calificacion calificacion = obtenerCalificacionPorId(id);
        Inscripciones inscripcion = obtenerInscripcion(request.idInscripcion());

        if (calificacion.cambioDatos(request.calificacion(), inscripcion)) {
            calificacion.actualizar(request.calificacion(), inscripcion);
        }
        return calificacionesMapper.entidadResponse(calificacion);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        log.info("Inscripciones eliminar");
        Calificacion calificacion = obtenerCalificacionPorId(id);
        calificacionesRepository.delete(calificacion);
    }

    private Calificacion obtenerCalificacionPorId(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(calificacionesRepository, id, Calificacion.class);
    }

    private Inscripciones obtenerInscripcion(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(inscripcionesRepository, id, Inscripciones.class);
    }
}
