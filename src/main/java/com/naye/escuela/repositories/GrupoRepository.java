package com.naye.escuela.repositories;

import com.naye.escuela.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    boolean existsByMaestroId(Long id);

    boolean existsByCursoId(Long id);

    boolean existsByAulaId(Long id);
}
