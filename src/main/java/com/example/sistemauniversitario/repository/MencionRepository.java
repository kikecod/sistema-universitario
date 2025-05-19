package com.example.sistemauniversitario.repository;

import com.example.sistemauniversitario.model.Mencion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MencionRepository extends JpaRepository<Mencion, Long>{
    Mencion findByNombre(String nombre);

}
