package com.example.sistemauniversitario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paralelo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paralelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paralelo;

    private String grupo;

    private String horario;

    private String dias;

    private String aula;

    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente docente;
}
