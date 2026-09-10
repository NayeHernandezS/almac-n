package com.naye.escuela.services.maestros;

import com.naye.escuela.dto.maestros.MaestroRequest;
import com.naye.escuela.dto.maestros.MaestroResponse;
import com.naye.escuela.entities.Maestro;
import com.naye.escuela.exceptions.EntidadRelacionExcepcion;
import com.naye.escuela.mappers.maestros.MaestroMapper;
import com.naye.escuela.repositories.GrupoRepository;
import com.naye.escuela.repositories.MaestroRepository;
import com.naye.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceMaestroImpl implements MaestroService {

    private final MaestroMapper maestroMapper;
    private final MaestroRepository maestroRepository;
    private final GrupoRepository grupoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MaestroResponse> listar() {
        log.info("Listando maestros");
        return maestroRepository.findAll().stream()
                .map(maestroMapper::entidadResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public MaestroResponse obtenerPorId(Long id) {
            log.info("Obteniendo maestro por id {}", id);
        return maestroMapper.entidadResponse(obtenerMaestroException(id));
    }

    @Transactional
    @Override
    public MaestroResponse registrar(MaestroRequest request) {

        log.info("Registrando maestro");
        validarDatosUnicos(request);

        Maestro maestro = maestroMapper.requestEntidad(request);
        maestroRepository.save(maestro);
        return maestroMapper.entidadResponse(maestro);
    }

    @Transactional
    @Override
    public MaestroResponse actualizar(MaestroRequest request, Long id) {

        log.info("Actualizando maestro por id {}", id);
        Maestro maestro = obtenerMaestroException(id);
        validarDatosUnicos(request, id);

        maestro.actualizar(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.email(),
                request.telefono()
        );
        return maestroMapper.entidadResponse(maestro);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        log.info("Eliminando maestro por id {}", id);
        Maestro maestro = obtenerMaestroException(id);
        if (grupoRepository.existsByMaestroId(id)) {
            throw new EntidadRelacionExcepcion("No se puede eliminar al maestro porque tiene grupos asignados");
        }
        maestroRepository.delete(maestro);
    }

    public Maestro obtenerMaestroException(Long id) {
        return ServiceUtils.obtenerEntidadOExcepetion(maestroRepository, id, Maestro.class);
    }

    private void validarDatosUnicos(MaestroRequest request) {
        validarDatosUnicos(request, null);
    }

    private void validarDatosUnicos(MaestroRequest request, Long idActual) {
        String email = request.email().trim();
        String telefono = request.telefono().trim();

        if (maestroRepository.existsByEmail(email, idActual)) {
            throw new IllegalArgumentException("Ya existe un maestro con el correo " + email);
        }
        if (maestroRepository.existsByTelefono(telefono, idActual)) {
            throw new IllegalArgumentException("Ya existe un maestro con el telefono " + telefono
                    + ". El telefono debe ser unico; usa otro numero o actualiza el maestro existente con PUT.");
        }
    }
}
