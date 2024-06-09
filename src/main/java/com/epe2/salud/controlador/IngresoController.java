package com.epe2.salud.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.epe2.salud.modelo.Ingreso;
import com.epe2.salud.repositorio.IngresoRepository;
import com.epe2.salud.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoController {

    @Autowired
    private IngresoRepository ingresoRepository;

    @GetMapping
    public List<Ingreso> getAllIngresos() {
        return ingresoRepository.findAll();
    }

    @PostMapping
    public Ingreso createIngreso(@RequestBody Ingreso ingreso) {
        return ingresoRepository.save(ingreso);
    }

    @GetMapping("/{id}")
    public Ingreso getIngresoById(@PathVariable Long id) {
        return ingresoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingreso no encontrado con id: " + id));
    }

    @PutMapping("/{id}")
    public Ingreso updateIngreso(@PathVariable Long id, @RequestBody Ingreso ingresoDetalles) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingreso no encontrado con id: " + id));

        ingreso.setPaciente(ingresoDetalles.getPaciente());
        ingreso.setMedico(ingresoDetalles.getMedico());
        ingreso.setEspecialidad(ingresoDetalles.getEspecialidad());
        ingreso.setDiagnostico(ingresoDetalles.getDiagnostico());
        ingreso.setFechaIngreso(ingresoDetalles.getFechaIngreso());
        ingreso.setFechaAlta(ingresoDetalles.getFechaAlta());
        return ingresoRepository.save(ingreso);
    }

    @DeleteMapping("/{id}")
    public void deleteIngreso(@PathVariable Long id) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingreso no encontrado con id: " + id));

        ingresoRepository.delete(ingreso);
    }
}
