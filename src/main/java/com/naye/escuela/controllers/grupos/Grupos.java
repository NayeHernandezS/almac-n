package com.naye.escuela.controllers.grupos;

import com.naye.escuela.controllers.CrudController;
import com.naye.escuela.dto.grupos.GrupoRequest;
import com.naye.escuela.dto.grupos.GrupoResponse;
import com.naye.escuela.services.grupos.GrupoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/grupos")
@Validated
public class Grupos extends CrudController<GrupoRequest, GrupoResponse, GrupoService> {
    public Grupos(GrupoService service) {
        super(service);
    }
}
