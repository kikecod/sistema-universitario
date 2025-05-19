package com.example.sistemauniversitario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mencion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mencion;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;
}
