package com.example.sistemauniversitario.repository;

import com.example.sistemauniversitario.model.Estudiante;
import com.example.sistemauniversitario.model.Inscripcion;
import com.example.sistemauniversitario.model.Paralelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    Optional<Inscripcion> findByEstudianteAndParalelo(Estudiante estudiante, Paralelo paralelo);
    List<Inscripcion> findAllByEstudiante(Estudiante estudiante);
    List<Inscripcion> findAllByParalelo(Paralelo paralelo);
    List<Inscripcion> findAllByActivoTrue();

}
