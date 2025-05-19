package com.example.sistemauniversitario.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InscripcionRequestDTO {

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long idEstudiante;

    @NotNull(message = "El ID del paralelo es obligatorio")
    private Long idParalelo;
}
