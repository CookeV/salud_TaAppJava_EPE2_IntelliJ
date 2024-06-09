package com.epe2.salud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epe2.salud.modelo.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}

