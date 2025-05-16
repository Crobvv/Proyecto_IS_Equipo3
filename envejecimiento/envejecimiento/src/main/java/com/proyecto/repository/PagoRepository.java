package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    // Posibles métodos personalizados si lo necesitas más adelante
}
