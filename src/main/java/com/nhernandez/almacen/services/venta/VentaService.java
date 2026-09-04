package com.nhernandez.almacen.services.venta;

import com.nhernandez.almacen.dto.ventas.VentaRequest;
import com.nhernandez.almacen.dto.ventas.VentaResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface VentaService {
    List<VentaResponse> listar();
    VentaResponse obtenerPorIdActiva(Long id);
    VentaResponse registrar(VentaRequest request);
    VentaResponse cancelar(Long id);

    @Transactional(readOnly = true)
    List<VentaResponse> listarCanceladas();
}
