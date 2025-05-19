package com.example.sistemauniversitario.repository;

import com.example.sistemauniversitario.model.Materia;
import com.example.sistemauniversitario.model.MateriaMencion;
import com.example.sistemauniversitario.model.MateriaMencionId;
import com.example.sistemauniversitario.model.Mencion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MateriaMencionRepository extends JpaRepository<MateriaMencion, MateriaMencionId> {

    MateriaMencion findByMateria(Materia materia);
    Optional<MateriaMencion> findByMateriaAndMencion(Materia materia, Mencion mencion);
}
