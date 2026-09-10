package com.naye.escuela.repositories;

import com.naye.escuela.entities.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroRepository extends JpaRepository<Maestro, Long> {

    @Query("""
            SELECT COUNT(m) > 0 FROM Maestro m
            WHERE LOWER(m.email) = LOWER(:email)
              AND (:id IS NULL OR m.id <> :id)
            """)
    boolean existsByEmail(@Param("email") String email, @Param("id") Long id);

    @Query("""
            SELECT COUNT(m) > 0 FROM Maestro m
            WHERE TRIM(m.telefono) = TRIM(:telefono)
              AND (:id IS NULL OR m.id <> :id)
            """)
    boolean existsByTelefono(@Param("telefono") String telefono, @Param("id") Long id);
}
