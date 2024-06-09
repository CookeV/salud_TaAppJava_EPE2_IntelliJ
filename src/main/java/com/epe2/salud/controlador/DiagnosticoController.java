package com.epe2.salud.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.epe2.salud.modelo.Diagnostico;
import com.epe2.salud.repositorio.DiagnosticoRepository;
import com.epe2.salud.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @GetMapping
    public List<Diagnostico> getAllDiagnosticos() {
        return diagnosticoRepository.findAll();
    }

    @PostMapping
    public Diagnostico createDiagnostico(@RequestBody Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    @GetMapping("/{id}")
    public Diagnostico getDiagnosticoById(@PathVariable Long id) {
        return diagnosticoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostico no encontrado con id: " + id));
    }

    @PutMapping("/{id}")
    public Diagnostico updateDiagnostico(@PathVariable Long id, @RequestBody Diagnostico diagnosticoDetalles) {
        Diagnostico diagnostico = diagnosticoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostico no encontrado con id: " + id));

        diagnostico.setNombre(diagnosticoDetalles.getNombre());
        return diagnosticoRepository.save(diagnostico);
    }

    @DeleteMapping("/{id}")
    public void deleteDiagnostico(@PathVariable Long id) {
        Diagnostico diagnostico = diagnosticoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostico no encontrado con id: " + id));

        diagnosticoRepository.delete(diagnostico);
    }
}