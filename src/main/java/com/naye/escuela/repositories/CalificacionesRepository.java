package com.naye.escuela.repositories;

import com.naye.escuela.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionesRepository extends JpaRepository<Calificacion, Long> {

    boolean existsByInscripcionesId(Long inscripcionId);
}
