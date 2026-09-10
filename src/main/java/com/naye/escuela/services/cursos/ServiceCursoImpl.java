package com.naye.escuela.services.cursos;

import com.naye.escuela.dto.cursos.CursoRequest;
import com.naye.escuela.dto.cursos.CursoResponse;
import com.naye.escuela.entities.Curso;
import com.naye.escuela.exceptions.EntidadRelacionExcepcion;
import com.naye.escuela.mappers.curso.CursoMapper;
import com.naye.escuela.repositories.CursoRepository;
import com.naye.escuela.repositories.GrupoRepository;
import com.naye.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceCursoImpl implements ServiceCurso {

    private final CursoMapper cursoMapper;
    private final CursoRepository cursoRepository;
    private final GrupoRepository grupoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CursoResponse> listar() {
        log.info("Inscripciones listar");
        return cursoRepository.findAll().stream()
                .map(cursoMapper::entidadResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CursoResponse obtenerPorId(Long id) {
        log.info("Inscripciones obtenerPorId");
        return cursoMapper.entidadResponse(obtenerCursoPorId(id));
    }

    @Transactional
    @Override
    public CursoResponse registrar(CursoRequest request) {
        log.info("Inscripciones registrar");
        if (cursoRepository.existsByNombreIgnoreCase(request.nombre().trim())) {
            throw new IllegalArgumentException("Ya existe un curso con ese nombre");
        }
        Curso curso = cursoMapper.requestEntidad(request);
        cursoRepository.save(curso);
        return cursoMapper.entidadResponse(curso);
    }

    @Transactional
    @Override
    public CursoResponse actualizar(CursoRequest request, Long id) {
        log.info("Inscripciones actualizar");
        Curso curso = obtenerCursoPorId(id);
        if (curso.cambioDatos(
                request.nombre(),
                request.descripcion(),
                request.creditos()
        )) {
            curso.actualizar(
                    request.nombre(),
                    request.descripcion(),
                    request.creditos()
            );
        }
        return cursoMapper.entidadResponse(curso);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        log.info("Inscripciones eliminar");
        Curso curso = obtenerCursoPorId(id);
        if (grupoRepository.existsByCursoId(id)) {
            throw new EntidadRelacionExcepcion("No se puede eliminar el curso porque tiene grupos asignados");
        } else {
            cursoRepository.delete(curso);
        }
    }

    public Curso obtenerCursoPorId(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(cursoRepository, id, Curso.class);
    }
}
