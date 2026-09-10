package com.naye.escuela.controllers.calificaciones;

import com.naye.escuela.controllers.CrudController;
import com.naye.escuela.dto.calificaciones.CalificacionesRequest;
import com.naye.escuela.dto.calificaciones.CalificacionesResponse;
import com.naye.escuela.services.calificaciones.CalificacionesService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/calificaciones")
@Validated
public class CalificacionesControllers extends CrudController<CalificacionesRequest, CalificacionesResponse, CalificacionesService> {
    public CalificacionesControllers(CalificacionesService service) {
        super(service);
    }
}
