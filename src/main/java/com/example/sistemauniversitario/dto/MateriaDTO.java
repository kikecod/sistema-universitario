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
public class MateriaDTO {

    @NotBlank(message = "El nombre de la materia es obligatorio")
    private String materia;

    @NotBlank(message = "La mención es obligatoria")
    private String mencion;

    @NotBlank(message = "La sigla es obligatoria")
    @Size(max = 10, message = "La sigla no puede tener más de 10 caracteres")
    private String sigla;

    @NotBlank(message = "El semestre es obligatorio")
    private String semestre;
}