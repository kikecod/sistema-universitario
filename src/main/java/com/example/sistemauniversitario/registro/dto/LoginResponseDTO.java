package com.example.sistemauniversitario.registro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private String token;
    private String username;
    private String rol;
}