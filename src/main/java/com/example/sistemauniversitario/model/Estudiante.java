package com.example.sistemauniversitario.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Estudiante extends Persona {

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String carrera;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;


}
