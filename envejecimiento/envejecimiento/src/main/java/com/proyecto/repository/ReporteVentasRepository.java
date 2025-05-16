package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.ReporteVentas;

@Repository
public interface ReporteVentasRepository extends JpaRepository<ReporteVentas, Long> {
    // posibles métodos personalizados si los necesitas
}