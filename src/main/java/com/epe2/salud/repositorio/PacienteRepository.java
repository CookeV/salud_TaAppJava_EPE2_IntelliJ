package com.epe2.salud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epe2.salud.modelo.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
