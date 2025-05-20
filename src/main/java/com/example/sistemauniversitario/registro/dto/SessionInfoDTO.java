package com.example.sistemauniversitario.registro.dto;

import com.example.sistemauniversitario.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class SessionInfoDTO {
    private String username;
    private String type = "Bearer";
    private String rol;
}