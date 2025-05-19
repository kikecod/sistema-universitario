package com.example.sistemauniversitario.controller;

import com.example.sistemauniversitario.dto.EstudianteDTO;
import com.example.sistemauniversitario.service.EstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listar(){
        return ResponseEntity.ok(estudianteService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtenerporid(@PathVariable Long id){
        return ResponseEntity.ok(estudianteService.obtenerporId(id));
    }
    @PostMapping
    public ResponseEntity<EstudianteDTO> crear (@Valid @RequestBody EstudianteDTO dto){
        return ResponseEntity.status(201).body(estudianteService.crear(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EstudianteDTO dto){
        return ResponseEntity.ok(estudianteService.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
