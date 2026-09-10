package com.naye.escuela.dto.maestros;

import com.naye.escuela.dto.DatosCurso;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MaestroResponse(

        @Schema(description = "Id del Maestro", example = "Ana")
        Long id,


        @Schema(description = "Nombre del Maestro", example = "Ana")
        String nombre,


        @Schema(description = "Correo electronico", example = "reyesAna12@gmail.com")
        String email,

        @Schema(description = "TELEFONO", example = "7352713050")
        String telefono,

        @Schema(description = "Datos de los cursos del maestro")
        List<DatosCurso> cursos

) {
}
