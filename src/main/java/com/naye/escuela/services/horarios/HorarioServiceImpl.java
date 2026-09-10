package com.naye.escuela.services.horarios;

import com.naye.escuela.dto.horarios.RequestHorarios;
import com.naye.escuela.dto.horarios.ResponseHorarios;
import com.naye.escuela.entities.Grupo;
import com.naye.escuela.entities.Horarios;
import com.naye.escuela.mappers.horarios.HorariosMappers;
import com.naye.escuela.repositories.GrupoRepository;
import com.naye.escuela.repositories.HorariosRepository;
import com.naye.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class HorarioServiceImpl implements HorarioService {

    private final HorariosMappers horariosMappers;
    private final HorariosRepository horariosRepository;
    private final GrupoRepository grupoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ResponseHorarios> listar() {
        log.info("Inscripciones listar");
        return horariosRepository.findAll().stream()
                .map(horariosMappers::entidadResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseHorarios obtenerPorId(Long id) {
        log.info("Inscripciones obtenerPorId");

        return horariosMappers.entidadResponse(obtenerHorariosPorId(id));
    }

    @Transactional
    @Override
    public ResponseHorarios registrar(RequestHorarios request) {

        log.info("Inscripciones registrar");
        Grupo grupo = obtenerGrupo(request.idGrupo());
        Horarios horarios = horariosMappers.requestEntidad(request, grupo);
        horariosRepository.save(horarios);
        return horariosMappers.entidadResponse(horarios);
    }

    @Transactional
    @Override
    public ResponseHorarios actualizar(RequestHorarios request, Long id) {
        log.info("Inscripciones actualizar");
        Horarios horarios = obtenerHorariosPorId(id);
        Grupo grupo = obtenerGrupo(request.idGrupo());

        if (horarios.cambioDatos(grupo, request.diaSemana(), request.horaInicio().trim(), request.horaFin().trim())) {
            horarios.actualizar(grupo, request.diaSemana(), request.horaInicio(), request.horaFin());
        }
        return horariosMappers.entidadResponse(horarios);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        log.info("Inscripciones eliminar");
        Horarios horarios = obtenerHorariosPorId(id);
        horariosRepository.delete(horarios);
    }

    public Horarios obtenerHorariosPorId(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(horariosRepository, id, Horarios.class);
    }

    private Grupo obtenerGrupo(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(grupoRepository, id, Grupo.class);
    }
}
