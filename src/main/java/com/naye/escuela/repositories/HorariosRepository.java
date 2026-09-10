package com.naye.escuela.repositories;

import com.naye.escuela.entities.Horarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorariosRepository extends JpaRepository<Horarios, Long> {

    boolean existsByGrupoId(Long grupoId);
}
