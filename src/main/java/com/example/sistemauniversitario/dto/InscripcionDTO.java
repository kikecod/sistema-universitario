package com.example.sistemauniversitario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionDTO implements Serializable {

    private Long id_inscripcion;

    @NotBlank(message = "El nombre del estudiante no puede estar vacío")
    private String estudiante;

    @NotBlank(message = "El nombre de la materia no puede estar vacío")
    private String materia;

    @NotBlank(message = "El nombre del paralelo no puede estar vacío")
    private String paralelo;

    @NotNull(message = "La fecha de inscripción no puede ser nula")
    @PastOrPresent(message = "La fecha debe ser pasada o presente")
    private LocalDate fechaInscripcion;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @Min(value = 0, message = "La nota mínima es 0")
    @Max(value = 100, message = "La nota máxima es 100")
    private Long notaFinal;
}