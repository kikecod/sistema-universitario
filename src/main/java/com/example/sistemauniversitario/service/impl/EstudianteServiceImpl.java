package com.example.sistemauniversitario.service.impl;

import com.example.sistemauniversitario.dto.EstudianteDTO;
import com.example.sistemauniversitario.model.Estudiante;
import com.example.sistemauniversitario.repository.EstudianteRepository;
import com.example.sistemauniversitario.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.sistemauniversitario.validation.EstudianteValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    private final EstudianteValidator estudianteValidator;

    private Estudiante mapearAEntidad(EstudianteDTO dto) {
        return Estudiante.builder()
                .id_persona(dto.getId())
                .ci(dto.getCi())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .fechaNacimieto(dto.getFechaNacimiento())
                .matricula(dto.getMatricula())
                .carrera(dto.getCarrera())
                .build();
    }
    private EstudianteDTO mapearADTO (Estudiante estudiante) {
        return EstudianteDTO.builder()
                .id(estudiante.getId_persona())
                .ci(estudiante.getCi())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail())
                .telefono(estudiante.getTelefono())
                .direccion(estudiante.getDireccion())
                .fechaNacimiento(estudiante.getFechaNacimieto())
                .matricula(estudiante.getMatricula())
                .carrera(estudiante.getCarrera())
                .build();
    }

    @Override
    public EstudianteDTO crear(EstudianteDTO dto) {
        if (!estudianteValidator.validarTelefono(dto.getTelefono())) {
            throw new RuntimeException("Teléfono inválido");
        }
        Estudiante estudiante = mapearAEntidad(dto);
        Estudiante guardado = estudianteRepository.save(estudiante);
        return mapearADTO(guardado);
    }

    @Override
    public List<EstudianteDTO> listar() {
        return estudianteRepository.findAllByActivoTrue().stream()
                .map(this::mapearADTO)
                .toList();
    }

    @Override
    public EstudianteDTO obtenerporId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .filter(Estudiante::getActivo)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado o inactivo"));
        return mapearADTO(estudiante);
    }
    @Override
    public EstudianteDTO actualizar(Long id, EstudianteDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado para actualizar"));

        estudiante.setCi(dto.getCi());
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setEmail(dto.getEmail());
        estudiante.setTelefono(dto.getTelefono());
        estudiante.setDireccion(dto.getDireccion());
        estudiante.setFechaNacimieto(dto.getFechaNacimiento());
        estudiante.setMatricula(dto.getMatricula());
        estudiante.setCarrera(dto.getCarrera());

        return mapearADTO(estudianteRepository.save(estudiante));
    }

    @Override
    public void eliminar(Long id){
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Estudiante no encontrado para eliminar"));
        estudiante.setActivo(false);
        estudianteRepository.save(estudiante);
    }



}
