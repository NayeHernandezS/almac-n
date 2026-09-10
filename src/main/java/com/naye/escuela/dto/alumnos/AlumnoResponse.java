package com.naye.escuela.dto.alumnos;

import com.naye.escuela.dto.DatosCalificacion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public record AlumnoResponse(

        Long id,

        @Schema(description = "Nombre del Alumno", example = "Nayely")
        String nombre,


        @Schema(description = "Correo electronico", example = "naye20_97@hotmail.com")
        String email,

        @Schema(description = "Matricula del Alumno", example = "20154004561")
        String matricula,

        @Schema(description = "Fecha de ingreso a la institucion", example = "26/09/2026")
        String fechaIngreso,

        @Schema(description = "Datos de las califiaciones")
        List<DatosCalificacion> calificaciones,

        @Schema( description = "Promedio del alumno", example = "9.9")
        BigDecimal promedio
) {
}
