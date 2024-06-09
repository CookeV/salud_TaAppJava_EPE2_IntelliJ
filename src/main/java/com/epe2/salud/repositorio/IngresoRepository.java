package com.epe2.salud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epe2.salud.modelo.Ingreso;

public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
}
