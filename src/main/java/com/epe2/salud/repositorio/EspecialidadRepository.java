package com.epe2.salud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epe2.salud.modelo.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
}
