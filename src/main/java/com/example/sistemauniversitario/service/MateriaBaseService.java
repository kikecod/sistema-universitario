package com.example.sistemauniversitario.service;

import com.example.sistemauniversitario.dto.MateriaBaseDTO;
import com.example.sistemauniversitario.dto.MateriaDTO;

import java.util.List;

public interface MateriaBaseService {
    List<MateriaBaseDTO> mostrar();
    MateriaBaseDTO obtenerPorId(Long id);
    MateriaBaseDTO crear(MateriaBaseDTO dto);
    MateriaBaseDTO actualizar(MateriaBaseDTO dto, Long id);
    void eliminar(Long id);
}
