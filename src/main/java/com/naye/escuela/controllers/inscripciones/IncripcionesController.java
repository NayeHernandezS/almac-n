package com.naye.escuela.controllers.inscripciones;

import com.naye.escuela.controllers.CrudController;
import com.naye.escuela.dto.inscripciones.IncripcionesRequest;
import com.naye.escuela.dto.inscripciones.IncripcionesResponse;

import com.naye.escuela.services.inscripciones.InscripcionesService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("api/inscripciones")
public class IncripcionesController extends CrudController<IncripcionesRequest, IncripcionesResponse, InscripcionesService> {
    public IncripcionesController(InscripcionesService service) {
        super(service);
    }
}
