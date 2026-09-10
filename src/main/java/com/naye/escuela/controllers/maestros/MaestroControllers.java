package com.naye.escuela.controllers.maestros;

import com.naye.escuela.controllers.CrudController;
import com.naye.escuela.dto.maestros.MaestroRequest;
import com.naye.escuela.dto.maestros.MaestroResponse;
import com.naye.escuela.services.maestros.MaestroService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/maestros")
@Validated
public class MaestroControllers extends CrudController<MaestroRequest, MaestroResponse, MaestroService> {
    public MaestroControllers(MaestroService service) {
        super(service);
    }
}
