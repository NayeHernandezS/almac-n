package com.naye.escuela.services.aulas;

import com.naye.escuela.dto.aula.AulaRequest;
import com.naye.escuela.dto.aula.AulaResponse;
import com.naye.escuela.entities.Aula;
import com.naye.escuela.exceptions.EntidadRelacionExcepcion;
import com.naye.escuela.mappers.AulaMapper;
import com.naye.escuela.repositories.AulaRepository;
import com.naye.escuela.repositories.GrupoRepository;
import com.naye.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AulaServiceIml implements AulaService {

    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;
    private final GrupoRepository grupoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AulaResponse> listar() {
        log.info("Iniciando lista de aulas");
        return aulaRepository.findAll().stream()
                .map(aulaMapper::entidadResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public AulaResponse obtenerPorId(Long id) {
        log.info("Iniciando obtencion de aula por id");
        return aulaMapper.entidadResponse(obtenerPorIdAula(id));
    }

    @Transactional
    @Override
    public AulaResponse registrar(AulaRequest request) {
        log.info("Iniciando registracion de aula por id");
        if (aulaRepository.existsByNombreIgnoreCase(request.nombre().trim())) {
            throw new IllegalArgumentException("Ya existe un aula con ese nombre");
        }
        Aula aula = aulaMapper.requestEntidad(request);
        aulaRepository.save(aula);
        return aulaMapper.entidadResponse(aula);
    }

    @Transactional
    @Override
    public AulaResponse actualizar(AulaRequest request, Long id) {
        log.info("Iniciando actualizacion de aula por id");
        Aula aula = obtenerPorIdAula(id);
        if (aula.cambioDatos(
                request.nombre(),
                request.capacidad()
        )) {
            aula.actualizar(
                    request.nombre(),
                    request.capacidad()
            );
        }
        return aulaMapper.entidadResponse(aula);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        log.info("Iniciando eliminacion de aula por id");
        Aula aula = obtenerPorIdAula(id);
        if (grupoRepository.existsByAulaId(id)) {
            throw new EntidadRelacionExcepcion("No se puede eliminar el aula porque tiene grupos asignados");
        }
        aulaRepository.delete(aula);
    }

    public Aula obtenerPorIdAula(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(aulaRepository, id, Aula.class);
    }
}
