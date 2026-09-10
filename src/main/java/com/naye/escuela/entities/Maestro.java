package com.naye.escuela.entities;

import com.naye.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Table(name = "MAESTROS")
@Entity
@NoArgsConstructor
@Getter@Builder
public class Maestro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID_MAESTRO")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "TELEFONO", nullable = false, length = 10, unique = true)
    private String telefono;

    @Builder.Default
    @OneToMany(mappedBy = "maestro", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();

    public void validarDatos(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono) {

        StringCustomUtils.validarTamanio(nombre, "tamanio invalido", 1, 50);

        StringCustomUtils.validarTamanio(apellidoPaterno, "tamanio invalido", 1, 50);

        StringCustomUtils.validarTamanio(apellidoMaterno, "tamanio invalido", 1, 50);

        StringCustomUtils.validarTamanio(email, "tamanio invalido", 1, 100);

        StringCustomUtils.validarTamanio(telefono, "tamanio invalido", 10, 10);
    }

    public void actualizar(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono) {
        validarDatos(nombre, apellidoPaterno, apellidoMaterno, email, telefono);
        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno = apellidoMaterno.trim();
        this.email = email.trim().toLowerCase();
        this.telefono = telefono.trim();
    }

}
