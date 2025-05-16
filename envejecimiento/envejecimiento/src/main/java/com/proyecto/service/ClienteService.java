package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Cliente;
import com.proyecto.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public Cliente registrarCliente(Cliente c) {
        return repo.save(c);
    }

    public List<Cliente> listarClientes() {
        return repo.findAll();
    }
}