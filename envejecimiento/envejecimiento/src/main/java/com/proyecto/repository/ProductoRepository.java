package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Categoria;
import com.proyecto.model.Festividad;
import com.proyecto.model.Producto;  // importa tu enum

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByFestividad(Festividad festividad);
}