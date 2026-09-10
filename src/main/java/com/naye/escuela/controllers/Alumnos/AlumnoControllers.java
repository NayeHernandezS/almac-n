package com.naye.escuela.controllers.Alumnos;

import com.naye.escuela.controllers.CrudController;
import com.naye.escuela.dto.alumnos.AlumnoRequest;
import com.naye.escuela.dto.alumnos.AlumnoResponse;
import com.naye.escuela.services.alumnos.ServiceAlumno;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/alumnos")
@Validated
public class AlumnoControllers extends CrudController<AlumnoRequest, AlumnoResponse, ServiceAlumno> {
    public AlumnoControllers(ServiceAlumno serviceAlumno){
        super(serviceAlumno);
    }

}
