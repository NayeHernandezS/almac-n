package com.naye.escuela.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GRUPOS", uniqueConstraints = @UniqueConstraint(
        columnNames = {"ID_CURSO", "ID_MAESTRO", "ID_AULA", "PERIDO"}
))
@Entity
@Getter@Builder
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MAESTRO", nullable = false)
    private Maestro maestro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AULA", nullable = false)
    private Aula aula;

    @Column(name = "PERIODO", length =20, nullable = false)
    private String periodo;

    public boolean cambioDatos(Curso curso, Maestro maestro, Aula aula, String periodo) {
        return !this.curso.getId().equals(curso.getId())
                || !this.maestro.getId().equals(maestro.getId())
                || !this.aula.getId().equals(aula.getId())
                || !this.periodo.equals(periodo);
    }

    public void actualizar(Curso curso, Maestro maestro, Aula aula, String periodo) {
        this.curso = curso;
        this.maestro = maestro;
        this.aula = aula;
        this.periodo = periodo.trim();
    }
}
