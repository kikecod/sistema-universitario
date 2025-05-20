package com.example.sistemauniversitario.registro.repository;

import com.example.sistemauniversitario.registro.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
