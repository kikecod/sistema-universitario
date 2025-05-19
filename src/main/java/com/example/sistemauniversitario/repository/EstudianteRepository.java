package com.example.sistemauniversitario.repository;

import com.example.sistemauniversitario.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    boolean existsByCi(String ci);
    boolean existsByEmail(String email);
    boolean existsByMatricula(String matricula);
    List<Estudiante> findAllByActivoTrue();
}
