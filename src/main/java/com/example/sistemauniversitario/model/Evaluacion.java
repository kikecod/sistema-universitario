package com.example.sistemauniversitario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evaluacion;

    @ManyToOne
    @JoinColumn(name = "id_paralelo", nullable = false)
    private Paralelo paralelo;

    private String nombre;

    private Long porcentaje;

    private Long sobre;

}
