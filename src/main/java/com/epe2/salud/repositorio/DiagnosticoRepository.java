package com.epe2.salud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epe2.salud.modelo.Diagnostico;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}
