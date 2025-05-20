package com.example.sistemauniversitario.registro.model;

import com.example.sistemauniversitario.model.Persona;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;



    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}