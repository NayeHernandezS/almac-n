package com.naye.escuela.controllers.cursos;

import com.naye.escuela.controllers.CrudController;
import com.naye.escuela.dto.cursos.CursoRequest;
import com.naye.escuela.dto.cursos.CursoResponse;
import com.naye.escuela.services.cursos.ServiceCurso;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cursos")
@Validated
public class CursoController extends CrudController<CursoRequest, CursoResponse, ServiceCurso> {
    public CursoController(ServiceCurso service) {
        super(service);
    }
}
