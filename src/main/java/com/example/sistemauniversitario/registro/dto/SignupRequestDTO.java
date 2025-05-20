package com.example.sistemauniversitario.registro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequestDTO {
    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;


    @NotBlank(message = "El rol es obligatorio")
    private String rol; // Ej: "ADMIN", "ESTUDIANTE", etc.
}