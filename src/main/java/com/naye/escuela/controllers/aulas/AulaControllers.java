package com.naye.escuela.controllers.aulas;

import com.naye.escuela.controllers.CrudController;
import com.naye.escuela.dto.aula.AulaRequest;
import com.naye.escuela.dto.aula.AulaResponse;
import com.naye.escuela.services.aulas.AulaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/aulas")
@Validated
public class AulaControllers extends CrudController<AulaRequest, AulaResponse, AulaService> {
    public AulaControllers(AulaService service){super(service);}
    
}
