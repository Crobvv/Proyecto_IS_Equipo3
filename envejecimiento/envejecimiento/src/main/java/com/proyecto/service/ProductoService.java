package com.proyecto.service;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Categoria;
import com.proyecto.model.Festividad;
import com.proyecto.model.Producto;
import com.proyecto.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto registrarProducto(Producto producto) {
        return save(producto);
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> buscarPorCategoria(String categoriaLabel) {
        if (categoriaLabel == null || categoriaLabel.isBlank()) {
            return listarTodos();
        }

        // Buscamos el enum cuyo label coincida (ignorando mayúsculas/minúsculas)
        Categoria categoriaEnum = null;
        for (Categoria c : Categoria.values()) {
            if (c.getLabel().equalsIgnoreCase(categoriaLabel.trim())) {
                categoriaEnum = c;
                break;
            }
        }

        // Si encontramos el enum, filtramos; si no, devolvemos lista vacía o todos
        if (categoriaEnum != null) {
            return productoRepository.findByCategoria(categoriaEnum);
        } else {
            return List.of();  // o listarTodos() si prefieres mostrar todo cuando no coincida
        }
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPorFestividad(String label) {
        if (label == null || label.isBlank()) {
            return listarTodos();
        }
        // Encuentra el enum cuyo toString() coincide con el label
        for (Festividad f : Festividad.values()) {
            if (f.toString().equalsIgnoreCase(label.trim())) {
                return productoRepository.findByFestividad(f);
            }
        }
        return List.of();  // o listarTodos()
    }
}