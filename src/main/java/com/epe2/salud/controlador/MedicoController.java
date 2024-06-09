package com.epe2.salud.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.epe2.salud.modelo.Medico;
import com.epe2.salud.repositorio.MedicoRepository;
import com.epe2.salud.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    @PostMapping
    public Medico createMedico(@RequestBody Medico medico) {
        return medicoRepository.save(medico);
    }

    @GetMapping("/{id}")
    public Medico getMedicoById(@PathVariable Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado con id: " + id));
    }

    @PutMapping("/{id}")
    public Medico updateMedico(@PathVariable Long id, @RequestBody Medico medicoDetalles) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado con id: " + id));

        medico.setNombre(medicoDetalles.getNombre());
        medico.setEspecialidad(medicoDetalles.getEspecialidad());
        return medicoRepository.save(medico);
    }

    @DeleteMapping("/{id}")
    public void deleteMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado con id: " + id));

        medicoRepository.delete(medico);
    }
}