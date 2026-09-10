package com.naye.escuela.entities;

import com.naye.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AULAS")
@AllArgsConstructor
@NoArgsConstructor
@Builder@Getter
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AULA")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "CAPACIDAD", nullable = false)
    private Integer capacidad;

    public boolean cambioDatos(String nombre, Integer capacidad){

        return !this.nombre.equals(nombre)||!this.capacidad.equals(capacidad);
    }

    public void validarDatos(String nombre, Integer capacidad) {

        StringCustomUtils.validarTamanio(nombre, "tamanio invalido", 1, 50);

        StringCustomUtils.validarTamanio(String.valueOf(capacidad), "tamanio invalido", 1, 50);

    }


    public void actualizar(String nombre, Integer capacidad){

        this.nombre = nombre.trim();
        this.capacidad= capacidad;
    }
}
