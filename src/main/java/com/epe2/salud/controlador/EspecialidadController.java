package com.epe2.salud.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.epe2.salud.modelo.Especialidad;
import com.epe2.salud.repositorio.EspecialidadRepository;
import com.epe2.salud.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @GetMapping
    public List<Especialidad> getAllEspecialidades() {
        return especialidadRepository.findAll();
    }

    @PostMapping
    public Especialidad createEspecialidad(@RequestBody Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @GetMapping("/{id}")
    public Especialidad getEspecialidadById(@PathVariable Long id) {
        return especialidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con id: " + id));
    }

    @PutMapping("/{id}")
    public Especialidad updateEspecialidad(@PathVariable Long id, @RequestBody Especialidad especialidadDetalles) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con id: " + id));

        especialidad.setNombre(especialidadDetalles.getNombre());
        return especialidadRepository.save(especialidad);
    }

    @DeleteMapping("/{id}")
    public void deleteEspecialidad(@PathVariable Long id) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con id: " + id));

        especialidadRepository.delete(especialidad);
    }
}