package com.example.sistemauniversitario.service.impl;

import com.example.sistemauniversitario.dto.MateriaBaseDTO;
import com.example.sistemauniversitario.dto.MateriaDTO;
import com.example.sistemauniversitario.model.Materia;
import com.example.sistemauniversitario.model.MateriaMencion;
import com.example.sistemauniversitario.model.Mencion;
import com.example.sistemauniversitario.repository.MateriaMencionRepository;
import com.example.sistemauniversitario.repository.MateriaRepository;
import com.example.sistemauniversitario.repository.MencionRepository;
import com.example.sistemauniversitario.service.MateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;

    private final MencionRepository mencionRepository;

    private final MateriaMencionRepository materiaMencionRepository;

    public MateriaDTO convertirADTO(Materia materia, Mencion mencion, MateriaMencion materiaMencion) {
        return MateriaDTO.builder()
                .materia(materia.getNombre())
                .mencion(mencion.getNombre())
                .sigla(materiaMencion.getSigla())
                .semestre(materiaMencion.getSemestre())
                .build();
    }
    public MateriaMencion convertirAEntidad(MateriaDTO materiaDTO){
        return MateriaMencion.builder()
                .materia(materiaRepository.findByNombre(materiaDTO.getMateria()))
                .mencion(mencionRepository.findByNombre(materiaDTO.getMencion()))
                .sigla(materiaDTO.getSigla())
                .semestre(materiaDTO.getSemestre())
                .build();
    }

    @Override
    public List<MateriaDTO> listar(){
        return materiaMencionRepository.findAll().stream()
                .map(materiaMencion -> convertirADTO(
                        materiaMencion.getMateria(),
                        materiaMencion.getMencion(),
                        materiaMencion
                        )
                )
                .toList();
    }
    public MateriaDTO crear (MateriaDTO materiaDTO) {
        Materia materia = materiaRepository.findByNombre(materiaDTO.getMateria());
        if (materia == null){
            throw new RuntimeException("Materia no encontrada");
        }
        Mencion mencion = mencionRepository.findByNombre(materiaDTO.getMencion());
        if (mencion == null){
            throw new RuntimeException("Mencion no encontrada");
        }

        Optional<MateriaMencion> existente = materiaMencionRepository.findByMateriaAndMencion(materia, mencion);
        if (existente.isPresent()) {
            throw new RuntimeException("Ya existe esa materia registrada en esa mención");
        }
        MateriaMencion materiaMencion = MateriaMencion.builder()
                .materia(materia)
                .mencion(mencion)
                .sigla(materiaDTO.getSigla())
                .semestre(materiaDTO.getSemestre())
                .build();
        materiaMencionRepository.save(materiaMencion);
        return convertirADTO(materia, mencion, materiaMencion);
    }




}
