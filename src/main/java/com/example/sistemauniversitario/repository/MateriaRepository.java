package com.example.sistemauniversitario.repository;

import com.example.sistemauniversitario.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    Materia findByNombre(String nombre);
    List<Materia> findAllByActivaTrue();
}
