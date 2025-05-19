package com.example.sistemauniversitario.repository;

import com.example.sistemauniversitario.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Docente findByNombre(String nombre);
    Docente findByCi(String ci);

}
