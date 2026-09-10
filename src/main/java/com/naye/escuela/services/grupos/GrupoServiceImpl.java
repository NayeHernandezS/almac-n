package com.naye.escuela.services.grupos;

import com.naye.escuela.dto.grupos.GrupoRequest;
import com.naye.escuela.dto.grupos.GrupoResponse;
import com.naye.escuela.entities.Aula;
import com.naye.escuela.entities.Curso;
import com.naye.escuela.entities.Grupo;
import com.naye.escuela.entities.Maestro;
import com.naye.escuela.exceptions.EntidadRelacionExcepcion;
import com.naye.escuela.mappers.grupos.GrupoMappers;
import com.naye.escuela.repositories.AulaRepository;
import com.naye.escuela.repositories.CursoRepository;
import com.naye.escuela.repositories.GrupoRepository;
import com.naye.escuela.repositories.HorariosRepository;
import com.naye.escuela.repositories.InscripcionesRepository;
import com.naye.escuela.repositories.MaestroRepository;
import com.naye.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GrupoServiceImpl implements GrupoService {

    private final GrupoMappers grupoMappers;
    private final GrupoRepository grupoRepository;
    private final CursoRepository cursoRepository;
    private final MaestroRepository maestroRepository;
    private final AulaRepository aulaRepository;
    private final InscripcionesRepository inscripcionesRepository;
    private final HorariosRepository horariosRepository;

    @Transactional(readOnly = true)
    @Override
    public List<GrupoResponse> listar() {
        return grupoRepository.findAll().stream()
                .map(grupoMappers::entidadResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public GrupoResponse obtenerPorId(Long id) {
        log.info("Inscripciones obtenerPorId");
        return grupoMappers.entidadResponse(obtenerGrupoPorId(id));
    }

    @Transactional
    @Override
    public GrupoResponse registrar(GrupoRequest request) {
        log.info("Inscripciones registrar");
        Curso curso = obtenerCurso(request.idCurso());
        Maestro maestro = obtenerMaestro(request.idMaestro());
        Aula aula = obtenerAula(request.idAula());

        Grupo grupo = grupoMappers.requestEntidad(request, curso, maestro, aula);
        grupoRepository.save(grupo);
        return grupoMappers.entidadResponse(grupo);
    }

    @Transactional
    @Override
    public GrupoResponse actualizar(GrupoRequest request, Long id) {
        log.info("Inscripciones actualizar");
        Grupo grupo = obtenerGrupoPorId(id);
        Curso curso = obtenerCurso(request.idCurso());
        Maestro maestro = obtenerMaestro(request.idMaestro());
        Aula aula = obtenerAula(request.idAula());

        if (grupo.cambioDatos(curso, maestro, aula, request.periodo().trim())) {
            grupo.actualizar(curso, maestro, aula, request.periodo());
        }
        return grupoMappers.entidadResponse(grupo);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        log.info("Inscripciones eliminar");
        Grupo grupo = obtenerGrupoPorId(id);
        if (inscripcionesRepository.existsByGrupoId(id) || horariosRepository.existsByGrupoId(id)) {
            throw new EntidadRelacionExcepcion("No se puede eliminar el grupo porque tiene inscripciones u horarios asignados");
        }
            grupoRepository.delete(grupo);

    }

    public Grupo obtenerGrupoPorId(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(grupoRepository, id, Grupo.class);
    }

    private Curso obtenerCurso(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(cursoRepository, id, Curso.class);
    }

    private Maestro obtenerMaestro(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(maestroRepository, id, Maestro.class);
    }

    private Aula obtenerAula(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(aulaRepository, id, Aula.class);
    }
}
