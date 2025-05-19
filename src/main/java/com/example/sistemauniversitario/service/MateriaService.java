package com.example.sistemauniversitario.service;

import com.example.sistemauniversitario.dto.MateriaBaseDTO;
import com.example.sistemauniversitario.dto.MateriaDTO;
import lombok.Builder;

import java.util.List;



public interface MateriaService {
    List<MateriaDTO> listar();
    MateriaDTO crear(MateriaDTO materiaDTO);

}
