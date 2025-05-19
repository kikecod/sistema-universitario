package com.example.sistemauniversitario.service;

import com.example.sistemauniversitario.dto.ParaleloDTO;

import java.util.List;

public interface ParaleloService {
    ParaleloDTO crear (ParaleloDTO dto);
    List<ParaleloDTO> listar();
}
