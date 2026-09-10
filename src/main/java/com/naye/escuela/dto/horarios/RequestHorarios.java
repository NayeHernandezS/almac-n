package com.naye.escuela.dto.horarios;

import com.naye.escuela.enums.DiaSemana;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RequestHorarios(

        @NotNull(message = "El grupo es requerido")
        @Positive(message = "El id del grupo debe ser positivo")
        @Schema(description = "Id del grupo", example = "1")
        Long idGrupo,

        @NotNull(message = "El dia de la semana es requerido")
        @Schema(description = "Dia de la semana", example = "LUNES")
        DiaSemana diaSemana,

        @NotBlank(message = "La hora de inicio es requerida")
        @Size(min = 5, max = 5, message = "La hora debe tener formato HH:mm")
        @Schema(description = "Hora de inicio", example = "08:00")
        String horaInicio,

        @NotBlank(message = "La hora de fin es requerida")
        @Size(min = 5, max = 5, message = "La hora debe tener formato HH:mm")
        @Schema(description = "Hora de fin", example = "10:00")
        String horaFin
) {
}
