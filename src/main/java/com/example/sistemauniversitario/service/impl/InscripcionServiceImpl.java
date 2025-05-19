package com.example.sistemauniversitario.service.impl;

import com.example.sistemauniversitario.dto.InscripcionDTO;
import com.example.sistemauniversitario.dto.InscripcionRequestDTO;
import com.example.sistemauniversitario.model.Estudiante;
import com.example.sistemauniversitario.model.Inscripcion;

import com.example.sistemauniversitario.model.Paralelo;
import com.example.sistemauniversitario.repository.EstudianteRepository;
import com.example.sistemauniversitario.repository.InscripcionRepository;
import com.example.sistemauniversitario.repository.MateriaRepository;
import com.example.sistemauniversitario.repository.ParaleloRepository;
import com.example.sistemauniversitario.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final ParaleloRepository paraleloRepository;

    // Implementar el método de inscripción
    @Override
    public InscripcionDTO inscribir (InscripcionRequestDTO inscripcionRequestDTO) {
        if (inscripcionRequestDTO.getIdEstudiante() == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Estudiante estudiante = estudianteRepository.findById(inscripcionRequestDTO.getIdEstudiante())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + inscripcionRequestDTO.getIdEstudiante()));

        Paralelo paralelo = paraleloRepository.findById(inscripcionRequestDTO.getIdParalelo())
                .orElseThrow(() -> new RuntimeException("Paralelo no encontrado con ID: " + inscripcionRequestDTO.getIdParalelo()));



        Optional<Inscripcion> existente = inscripcionRepository.findByEstudianteAndParalelo(estudiante, paralelo);
        if (existente.isPresent()) {
            throw new RuntimeException("El estudiante ya está inscrito en este paralelo.");
        }




        Inscripcion inscripcion = Inscripcion.builder()
                .estudiante(estudiante)
                .paralelo(paralelo)
                .fechaInscripcion(java.time.LocalDate.now())
                .estado("activo")
                .notaFinal(null)
                .build();

        inscripcion = inscripcionRepository.save(inscripcion);
        return convertir(inscripcion, estudiante, paralelo);
    }
    @Override
    public List<InscripcionDTO> listar (){
        return inscripcionRepository.findAllByActivoTrue().stream()
                .map( inscripcion -> convertir(
                        inscripcion,
                        inscripcion.getEstudiante(),
                        inscripcion.getParalelo()
                ))
                .toList();
    }

    @Override
    public List<InscripcionDTO> obtenerPorIdEstudiante(Long idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return inscripcionRepository.findAllByEstudiante(estudiante).stream()
                .filter(Inscripcion::getActivo)
                .map(i -> convertir(i, i.getEstudiante(), i.getParalelo()))
                .toList();
    }

    @Override
    public List<InscripcionDTO> obtenerPorIdParalelo (Long idParalelo){
        Paralelo paralelo = paraleloRepository.findById(idParalelo)
                .orElseThrow(() -> new RuntimeException("Paralelo no encontrado"));

        return inscripcionRepository.findAllByParalelo(paralelo).stream()
                .filter(Inscripcion::getActivo)
                .map(i -> convertir(i, i.getEstudiante(), i.getParalelo()))
                .toList();
    }

    @Override
    public InscripcionDTO obtenerPorId (Long id){
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripcion no encontrado"));
        return convertir(inscripcion, inscripcion.getEstudiante(), inscripcion.getParalelo());
    }

    @Override
    public InscripcionDTO actualizar (Long id, InscripcionDTO inscripcionDTO) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripcion no encontrado"));

        if (inscripcionDTO.getNotaFinal() >= 0 && inscripcionDTO.getNotaFinal() < 101) {
            inscripcion.setNotaFinal(inscripcionDTO.getNotaFinal());}
        else {
            throw new RuntimeException("La nota final debe estar entre 0 y 100");
        }

        inscripcion.setEstudiante(inscripcion.getEstudiante());
        inscripcion.setParalelo(inscripcion.getParalelo());
        inscripcion.setEstado(inscripcionDTO.getEstado());

        return convertir(
                inscripcionRepository.save(inscripcion),
                inscripcion.getEstudiante(),
                inscripcion.getParalelo()
        );
    }
    @Override
    public void eliminar (Long id){
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripcion no encontrado"));
        inscripcion.setActivo(false);
        inscripcionRepository.save(inscripcion);
    }

    public InscripcionDTO convertirADTO (Inscripcion inscripcion, Estudiante estudiante, Paralelo paralelo) {


        return InscripcionDTO.builder()
                .id_inscripcion(inscripcion.getId_inscripcion())
                .estudiante(estudiante.getNombre() + " "+ estudiante.getApellido())
                .paralelo(paralelo.getGrupo()+ " - " + paralelo.getMateria().getNombre())
                .fechaInscripcion(inscripcion.getFechaInscripcion())
                .estado(inscripcion.getEstado())
                .notaFinal(inscripcion.getNotaFinal())
                .build();
    }

    public InscripcionDTO convertir (Inscripcion inscripcion, Estudiante estudiante, Paralelo paralelo) {


        return InscripcionDTO.builder()
                .id_inscripcion(inscripcion.getId_inscripcion())
                .estudiante(estudiante.getNombre() + " "+ estudiante.getApellido())
                .materia(paralelo.getMateria().getNombre())
                .paralelo(paralelo.getGrupo())
                .fechaInscripcion(inscripcion.getFechaInscripcion())
                .estado(inscripcion.getEstado())
                .notaFinal(inscripcion.getNotaFinal())
                .build();
    }


}
