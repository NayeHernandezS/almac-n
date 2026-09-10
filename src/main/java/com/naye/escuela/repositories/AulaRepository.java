package com.naye.escuela.repositories;

import com.naye.escuela.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    boolean existsByNombreIgnoreCase(String nombre);
}
