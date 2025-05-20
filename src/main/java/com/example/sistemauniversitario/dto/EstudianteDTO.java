package com.example.sistemauniversitario.dto;


import com.example.sistemauniversitario.validation.ValidCI;
import lombok.*;

import java.time.LocalDate;

import java.io.Serializable;

import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EstudianteDTO implements Serializable {

    private Long id;

    @NotBlank(message = "El campo CI no puede estar vacío")
    @ValidCI
    private String ci;

    @NotBlank(message = "El campo nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede estar vacío")
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    private String apellido;

    @NotBlank(message = "El campo email no puede estar vacio")
    @Email(message = "El email no es valido")
    @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    private String email;

    @NotBlank(message = "El campo telefono no puede estar vacio")
    @Size(min = 8, max = 15, message = "El telefono debe tener entre 8 y 15 caracteres")
    private String telefono;

    @NotBlank(message = "El campo direccion no puede estar vacio")
    @Size(max = 100, message = "La direccion no puede tener más de 50 caracteres")
    private String direccion;

    private LocalDate fechaNacimiento;

    @NotNull(message = "El campo de matricula tiene que ser obligatorio")
    private String matricula;

    @NotBlank(message = "El estudiante tiene que tener una carrera asignada")
    private String carrera;

}
