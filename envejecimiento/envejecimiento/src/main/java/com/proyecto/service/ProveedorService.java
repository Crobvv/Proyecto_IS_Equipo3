package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Proveedor;
import com.proyecto.repository.ProveedorRepository;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public Proveedor registrarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerPorId(int id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor buscarPorRfc(String rfc) {
        return proveedorRepository.findByRfc(rfc);
    }

    public void eliminarProveedor(int id) {
        proveedorRepository.deleteById(id);
    }
}