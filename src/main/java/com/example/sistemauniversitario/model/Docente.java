package com.example.sistemauniversitario.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name= "docente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Docente extends Persona {
    @Column(name = "titulo_academico", nullable = false)
    private String tituloAcademico;
}
