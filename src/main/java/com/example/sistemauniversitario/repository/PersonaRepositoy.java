package com.example.sistemauniversitario.repository;

import com.example.sistemauniversitario.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositoy extends JpaRepository<Persona, Long> {
}
