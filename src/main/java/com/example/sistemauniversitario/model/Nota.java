package com.example.sistemauniversitario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nota")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_nota;

    @ManyToOne
    @JoinColumn(name = "id_evaluacion", nullable = false)
    private Evaluacion evaluacion;

    @ManyToOne
    @JoinColumn(name = "id_inscripcion", nullable = false)
    private Inscripcion inscripcion;

    private Long nota;
}

