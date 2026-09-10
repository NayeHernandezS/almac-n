package com.naye.escuela.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CURSOS")
@Entity
@Builder@Getter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    @Column(name = "CREDITOS", nullable = false)
    private Integer creditos;

    public boolean cambioDatos(String nombre, String descripcion, Integer creditos) {
        return !Objects.equals(this.nombre, nombre)
                || !Objects.equals(this.descripcion, descripcion)
                || !Objects.equals(this.creditos, creditos);
    }

    public void actualizar(String nombre, String descripcion, Integer creditos) {
        this.nombre = nombre.trim();
        this.descripcion = descripcion != null ? descripcion.trim() : null;
        this.creditos = creditos;
    }
}
