package com.example.sistemauniversitario.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "inscripcion", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_estudiante", "id_paralelo"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_inscripcion;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_paralelo", nullable = false)
    private Paralelo paralelo;

    private LocalDate fechaInscripcion;

    private String estado; // otros valores: retirado, anulado

    @Column(name = "nota_final")
    private Long notaFinal;

    @Builder.Default
    private Boolean activo = true;

}
