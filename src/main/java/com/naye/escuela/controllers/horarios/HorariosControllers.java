package com.naye.escuela.controllers.horarios;

import com.naye.escuela.controllers.CrudController;
import com.naye.escuela.dto.horarios.RequestHorarios;
import com.naye.escuela.dto.horarios.ResponseHorarios;
import com.naye.escuela.services.horarios.HorarioService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/horarios")
@Validated
public class HorariosControllers extends CrudController<RequestHorarios, ResponseHorarios, HorarioService> {
    public HorariosControllers(HorarioService service) {
        super(service);
    }
}
