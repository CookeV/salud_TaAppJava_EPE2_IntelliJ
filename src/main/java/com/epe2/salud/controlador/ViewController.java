package com.epe2.salud.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.epe2.salud.repositorio.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

    @GetMapping ("/")
    public String index(Model model) {
        model.addAttribute("pacientes", pacienteRepository.findAll());
        model.addAttribute("medicos", medicoRepository.findAll());
        model.addAttribute("especialidades", especialidadRepository.findAll());
        model.addAttribute("diagnosticos", diagnosticoRepository.findAll());
        model.addAttribute("ingresos", ingresoRepository.findAll());
        return "index";
    }
}

