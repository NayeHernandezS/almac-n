package com.naye.escuela.repositories;

import com.naye.escuela.entities.Inscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionesRepository extends JpaRepository<Inscripciones, Long> {

    boolean existsByAlumnoId(Long alumnoId);

    boolean existsByGrupoId(Long grupoId);

    @Query("""
            SELECT DISTINCT i FROM Inscripciones i
            JOIN FETCH i.alumno
            JOIN FETCH i.grupo g
            JOIN FETCH g.curso
            LEFT JOIN FETCH i.calificacion
            """)
    List<Inscripciones> findAllConRelaciones();

    @Query("""
            SELECT DISTINCT i FROM Inscripciones i
            JOIN FETCH i.alumno
            JOIN FETCH i.grupo g
            JOIN FETCH g.curso
            LEFT JOIN FETCH i.calificacion
            WHERE i.id = :id
            """)
    Optional<Inscripciones> findByIdConRelaciones(@Param("id") Long id);
}
