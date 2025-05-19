package com.example.sistemauniversitario.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "materia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_materia;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    private String area;

    @Builder.Default
    private Boolean activa = true;
}
