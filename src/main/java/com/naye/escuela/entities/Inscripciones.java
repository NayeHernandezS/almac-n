package com.naye.escuela.entities;

import com.naye.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "INSCRIPCIONES", uniqueConstraints = @UniqueConstraint(
        name = "INSCRIPCION_ALU_GRU_UK",
        columnNames = {"ID_ALUMNO", "ID_GRUPO"}
))
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Builder
public class Inscripciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INSCRIPCION")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ALUMNO", nullable = false)
    private Alumno alumno;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO", nullable = false)
    private Grupo grupo;

    @Builder.Default
    @Column(name = "FECHAINSCRIPCION")
    private LocalDate fechaIncripcion = LocalDate.now();


    @OneToOne(mappedBy = "inscripciones")
    private Calificacion calificacion;

    public boolean cambioDatos(Alumno alumno, Grupo grupo) {
        return !this.alumno.getId().equals(alumno.getId())
                || !this.grupo.getId().equals(grupo.getId());
    }

    public void validarDatos(Alumno alumno, Grupo grupo, LocalDate fechaIncripcion, Calificacion calificacion) {
        StringCustomUtils.validarTamanio(String.valueOf(alumno), "tamanio invalido", 1, 50);
        StringCustomUtils.validarTamanio(String.valueOf(grupo), "tamanio invalido", 1, 10);
        if (calificacion != null) {
            StringCustomUtils.validarTamanio(calificacion.toString(), "tamanio invalido", 1, 2);
        }
    }

    public void actualizar(Alumno alumno, Grupo grupo) {
        validarDatos(alumno, grupo, this.fechaIncripcion, this.calificacion);
        this.alumno = alumno;
        this.grupo = grupo;
    }
}
