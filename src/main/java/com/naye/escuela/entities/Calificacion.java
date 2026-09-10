package com.naye.escuela.entities;

import com.naye.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "CALIFICACIONES")
@Entity
@Getter
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CALIFICACION")
    private  Long id;

    @Column(name = "CALIFICACION", nullable = false)
    private BigDecimal calificacion;

    @Builder.Default
    @Column(name = "FECHA_REGISTRO", nullable = false)
    private LocalDate fechaRegistro= LocalDate.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INSCRIPCION", nullable = false, unique = true)
    private Inscripciones inscripciones;

    public boolean cambioDatos(BigDecimal calificacion, Inscripciones inscripciones) {
        return this.calificacion.compareTo(calificacion) != 0
                || !this.inscripciones.getId().equals(inscripciones.getId());
    }

    public void validarDatos(BigDecimal calificacion, LocalDate fechaRegistro, Inscripciones inscripciones) {
        StringCustomUtils.validarTamanio(String.valueOf(calificacion), "tamanio invalido", 1, 10);
    }

    public void actualizar(BigDecimal calificacion, Inscripciones inscripciones) {
        validarDatos(calificacion, this.fechaRegistro, inscripciones);
        this.calificacion = calificacion;
        this.inscripciones = inscripciones;
    }
}
