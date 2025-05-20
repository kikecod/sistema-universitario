package com.example.sistemauniversitario.service.impl;

import com.example.sistemauniversitario.dto.MateriaBaseDTO;
import com.example.sistemauniversitario.model.Materia;
import com.example.sistemauniversitario.repository.MateriaRepository;
import com.example.sistemauniversitario.service.MateriaBaseService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaBaseServiceImpl implements MateriaBaseService {

    private final MateriaRepository materiaRepository;

    public Materia convertirAEntidad(MateriaBaseDTO dto){
        return Materia.builder()
                .id_materia(dto.getId_materia())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .area(dto.getArea())
                .build();
    }
    public MateriaBaseDTO convertirADTO(Materia materia){
        return MateriaBaseDTO.builder()
                .id_materia(materia.getId_materia())
                .nombre(materia.getNombre())
                .descripcion(materia.getDescripcion())
                .area(materia.getArea())
                .build();
    }

    @CacheEvict(value = "materias", allEntries = true)
    @Override
    public List<MateriaBaseDTO> mostrar(){
        return materiaRepository.findAllByActivaTrue().stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public MateriaBaseDTO obtenerPorId(Long id){
        Materia materia = materiaRepository.findById(id)
                .filter(Materia::getActiva)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        return convertirADTO(materia);
    }

    @Override
    public MateriaBaseDTO crear(MateriaBaseDTO dto) {
        Materia materia = convertirAEntidad(dto);
        Materia guardado = materiaRepository.save(materia);
        return convertirADTO(guardado);
    }

    @CacheEvict(value = "materias", allEntries = true)
    @Override
    public MateriaBaseDTO actualizar(MateriaBaseDTO dto, Long id){
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        materia.setNombre(dto.getNombre());
        materia.setDescripcion(dto.getDescripcion());
        materia.setArea(dto.getArea());

        return convertirADTO(materiaRepository.save(materia));
    }

    @CacheEvict(value = "materias", allEntries = true)
    @Override
    public void eliminar(Long id){
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        materia.setActiva(false);
        materiaRepository.save(materia);
    }

}
