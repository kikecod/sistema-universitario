package com.example.sistemauniversitario.service;

import com.example.sistemauniversitario.dto.EstudianteDTO;
import com.example.sistemauniversitario.model.Estudiante;

import java.util.List;

public interface EstudianteService {
    EstudianteDTO crear (EstudianteDTO dto);
    List<EstudianteDTO> listar();
    EstudianteDTO obtenerporId(Long id);
    EstudianteDTO actualizar(Long id, EstudianteDTO dto);
    void eliminar (Long id);
}
