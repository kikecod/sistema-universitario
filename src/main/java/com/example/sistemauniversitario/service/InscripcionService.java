package com.example.sistemauniversitario.service;

import com.example.sistemauniversitario.dto.InscripcionDTO;
import com.example.sistemauniversitario.dto.InscripcionRequestDTO;

import java.util.List;

public interface InscripcionService {

    InscripcionDTO inscribir (InscripcionRequestDTO inscripcionRequestDTO);
    List<InscripcionDTO> listar();
    List<InscripcionDTO> obtenerPorIdEstudiante(Long idEstudiante);
    InscripcionDTO actualizar (Long id, InscripcionDTO inscripcionDTO);
    void eliminar (Long id);
    InscripcionDTO obtenerPorId (Long id);
    List<InscripcionDTO> obtenerPorIdParalelo (Long idParalelo);
}
