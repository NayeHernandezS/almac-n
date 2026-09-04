package com.nhernandez.almacen.services.sucursales;

import com.nhernandez.almacen.dto.sucursales.SucursalesRequest;
import com.nhernandez.almacen.dto.sucursales.SucursalesResponse;
import com.nhernandez.almacen.entities.Sucursal;
import com.nhernandez.almacen.exceptions.RecursoNoEncontradoException;
import com.nhernandez.almacen.mappers.SucursalesMapper;
import com.nhernandez.almacen.repositories.SucursalesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class SucursalesServiceImpl implements SucursalesService{

    private final SucursalesRepository sucursalesRepository;
   private final SucursalesMapper sucursalesMapper;



    @Override
    public List<SucursalesResponse> listar() {
        log.info("Listando las sucursales");
        return sucursalesRepository.findAll().stream()
                .map(sucursalesMapper::entidadResponseSucursal).toList();
    }

    @Override
    public SucursalesResponse obtenerPorId(Long id) {
        log.info("Obteniendo la sucursal con el id" + id);
        return sucursalesMapper.entidadResponseSucursal(obtenerPorIdException(id));
    }

    @Override
    public SucursalesResponse crearSucursal(SucursalesRequest request) {
        Sucursal sucursal = sucursalesMapper.requestEntidadSucursal(request);
        validarDatosUnicos(request);
        sucursalesRepository.save(sucursal);
        log.info("Creando una nueva sucursal");
        return sucursalesMapper.entidadResponseSucursal(sucursal);
    }

    @Override
    public SucursalesResponse actualizarSucursal(SucursalesRequest request, Long id) {
        Sucursal sucursal = obtenerPorIdException(id);

        validarCambiosUnicos(request, id);
        sucursal.actualizar(
                request.nombre(),
                request.direccion()
        );
        log.info("Actualizando la sucursal con el id: " + id);
        return sucursalesMapper.entidadResponseSucursal(sucursal);
    }

    @Override
    public void eliminarSucursal(Long id) {
        Sucursal sucursal = obtenerPorIdException(id);
        log.info("Eliminando la sucursal con el ID:" + id);
        sucursalesRepository.delete(sucursal);
    }


    private Sucursal obtenerPorIdException(Long id){
        return sucursalesRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("La sucursal con id: " + id +"No existe!"));
    }

    private  void validarDatosUnicos(SucursalesRequest request){
        if (sucursalesRepository.existsByNombreIgnoreCase(request.nombre().trim()))
            throw  new IllegalArgumentException("Ya existe una sucursal con el nombre de:" + request.nombre());
    }


    private  void validarCambiosUnicos(SucursalesRequest request, Long id){
        if (sucursalesRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(), id))
            throw  new IllegalArgumentException("Ya existe una sucursal con el nombre de:" + request.nombre());
    }
}
