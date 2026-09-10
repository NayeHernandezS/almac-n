package com.naye.escuela.entities;

import com.naye.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Table(name = "ALUMNOS")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder@Getter
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID_ALUMNO", nullable = false, length = 50)
    private Long id;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "APELLIDO_PATERNO", nullable = false, length = 50)
    private String aPaterno;
    @Column(name = "APELLIDO_MATERNO", nullable = false, length = 50)
    private String aMaterno;
    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;
    @Column(name = "MATRICULA", nullable = false, length = 20)
    private String matricula;
    @Column(name = "FECHA_INGRESO", nullable = false)
    private Date fechaIngreso;

    @Builder.Default
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Inscripciones> inscripciones = new ArrayList<>();

    public void validarDatos(String nombre, String apellidoPaterno, String apellidoMaterno, String email) {

        StringCustomUtils.validarTamanio(nombre, "tamanio invalido", 1, 50);

        StringCustomUtils.validarTamanio(apellidoPaterno, "tamanio invalido", 1, 50);

        StringCustomUtils.validarTamanio(apellidoMaterno, "tamanio invalido", 1, 50);

        StringCustomUtils.validarTamanio(email, "tamanio invalido", 1, 100);

    }

    public boolean cambioDatos(String nombre, String apellidoPaterno, String apellidoMaterno){

        return !this.nombre.equals(nombre)||!this.aPaterno.equals(apellidoPaterno)||!this.aMaterno.equals(apellidoMaterno);
         }

    public void asignarDatosAcademicos(String email, String matricula){
        StringCustomUtils.validarTamanio(email, "Debe tener entre 1 y 100 caracteres", 1, 100);
        StringCustomUtils.validarTamanio(matricula, "Debe tener entre 1 y 20 caracteres", 1, 20);

        this.email = email.toLowerCase().trim();
        this.matricula = matricula.trim();
    }

    public void actualizar(String nombre, String apellidoPaterno, String apellidoMaterno,
                           String email, String matricula){
        validarDatos(nombre, apellidoPaterno, apellidoMaterno, email);
        asignarDatosAcademicos(email, matricula);

        this.nombre = nombre.trim();
        this.aPaterno = apellidoPaterno.trim();
        this.aMaterno = apellidoMaterno.trim();
    }


    public BigDecimal calcularPromedio(){
        List<BigDecimal> calificaciones = inscripciones.stream()
                .map(Inscripciones::getCalificacion)
                .filter(Objects::nonNull)
                .map(Calificacion::getCalificacion)
                .filter(Objects::nonNull).toList();

        if (calificaciones.isEmpty())
            return BigDecimal.ZERO;

        BigDecimal suma = calificaciones.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return suma.divide(BigDecimal.valueOf(calificaciones.size()),
        2, RoundingMode.HALF_UP);
    }
}
