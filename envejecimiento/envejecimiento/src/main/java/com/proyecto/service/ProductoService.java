package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Producto;
import com.proyecto.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Persiste o actualiza un producto en la base de datos.
     */
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Método existente para registrar producto
     * (puedes dejarlo o llamarlo internamente a save())
     */
    public Producto registrarProducto(Producto producto) {
        return save(producto);
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }
}
