package com.naye.escuela.dto.horarios;

import com.naye.escuela.enums.DiaSemana;
import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseHorarios(

        @Schema(description = "Id del horario", example = "1")
        Long id,

        @Schema(description = "Id del grupo", example = "1")
        Long idGrupo,

        @Schema(description = "Dia de la semana", example = "LUNES")
        DiaSemana diaSemana,

        @Schema(description = "Hora de inicio", example = "08:00")
        String horaInicio,

        @Schema(description = "Hora de fin", example = "10:00")
        String horaFin
) {
}
