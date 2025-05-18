package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.model.Cliente;
import com.proyecto.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Cliente registrarCliente(Cliente cliente) {
        // Codificar la contraseña antes de guardarla
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente validarCredenciales(String email, String password) {
        Optional<Cliente> optcliente = clienteRepository.findByCorreo(email);
        if(optcliente.isPresent()) {
            Cliente cliente = optcliente.get();

            // Comparamos usando el encoder (no con equals)
            if(passwordEncoder.matches(password, cliente.getPassword())) {
                return cliente;
            }
        }
        return null;
    }
}