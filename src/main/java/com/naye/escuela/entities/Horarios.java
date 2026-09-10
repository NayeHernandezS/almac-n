package com.naye.escuela.entities;

import com.naye.escuela.enums.DiaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HORARIOS")
@Entity
@Builder@Getter
public class Horarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HORARIO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_GRUPO", nullable = false)
    private  Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIA", nullable = false)
    private DiaSemana diaSemana;

    @Column(name = "HORA_INICIO", length = 5, nullable = false)
    private String horaInicio;

    @Column(name = "HORA_FIN", length = 5, nullable = false)
    private String horaFin;

    public boolean cambioDatos(Grupo grupo, DiaSemana diaSemana, String horaInicio, String horaFin) {
        return !this.grupo.getId().equals(grupo.getId())
                || this.diaSemana != diaSemana
                || !this.horaInicio.equals(horaInicio)
                || !this.horaFin.equals(horaFin);
    }

    public void actualizar(Grupo grupo, DiaSemana diaSemana, String horaInicio, String horaFin) {
        this.grupo = grupo;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio.trim();
        this.horaFin = horaFin.trim();
    }
}
