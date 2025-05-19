package com.example.sistemauniversitario.controller;

import com.example.sistemauniversitario.dto.MateriaBaseDTO;
import com.example.sistemauniversitario.dto.MateriaDTO;
import com.example.sistemauniversitario.repository.MateriaRepository;
import com.example.sistemauniversitario.service.MateriaBaseService;
import com.example.sistemauniversitario.service.MateriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/materias")
@RequiredArgsConstructor
public class MateriaController {
    private final MateriaService materiaService;
    private final MateriaBaseService materiaBaseService;

    @GetMapping
    public ResponseEntity<List<MateriaBaseDTO>> mostrar(){
        return ResponseEntity.ok(materiaBaseService.mostrar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MateriaBaseDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(materiaBaseService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<MateriaBaseDTO> crear(@Valid @RequestBody MateriaBaseDTO dto){
        return ResponseEntity.status(201).body(materiaBaseService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaBaseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MateriaBaseDTO dto){
        return ResponseEntity.ok(materiaBaseService.actualizar(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MateriaBaseDTO> eliminar(@PathVariable Long id){
        materiaBaseService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/materia-mencion")
    public ResponseEntity<List<MateriaDTO>> listar(){
        return ResponseEntity.ok(materiaService.listar());
    }

    @PostMapping("/materia-mencion")
    public ResponseEntity<MateriaDTO> crear(@Valid @RequestBody MateriaDTO dto){
        return ResponseEntity.status(201).body(materiaService.crear(dto));
    }



}
