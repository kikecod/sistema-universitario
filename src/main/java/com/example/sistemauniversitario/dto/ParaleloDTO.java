package com.example.sistemauniversitario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParaleloDTO {

    private Long id_paralelo;

    @NotBlank(message = "El grupo es obligatorio")
    @Size(max = 5, message = "El grupo no puede tener más de 5 caracteres")
    private String grupo;

    @NotBlank(message = "El horario es obligatorio")
    private String horario;

    @NotBlank(message = "Los días son obligatorios")
    private String dias;

    @NotBlank(message = "El aula es obligatoria")
    private String aula;

    @NotBlank(message = "El nombre de la materia es obligatorio")
    private String nombreMateria;

    @NotBlank(message = "El CI del docente es obligatorio")
    private String Docente;
}