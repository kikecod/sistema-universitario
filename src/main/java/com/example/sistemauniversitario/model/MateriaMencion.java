package com.example.sistemauniversitario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materia_mencion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MateriaMencionId.class)
@Builder
public class MateriaMencion {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_mencion")
    private Mencion mencion;

    @Column(nullable = false, unique = true)
    private String sigla;


    private String semestre;


}
