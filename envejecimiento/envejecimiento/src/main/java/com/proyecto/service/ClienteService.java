package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
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

    /**
     * Registra un nuevo cliente.
     * @throws IllegalArgumentException si ya existe un cliente con el mismo correo.
     */
    public Cliente registrarCliente(Cliente cliente) {
        Optional<Cliente> existente = clienteRepository.findByCorreo(cliente.getCorreo());
        if (existente.isPresent()) {
            throw new IllegalArgumentException(
                "Ya existe un cliente registrado con el correo " + cliente.getCorreo()
            );
        }
        // Codificar la contraseña antes de guardarla
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    /** Lista todos los clientes. */
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Valida credenciales de acceso.
     * @return el Cliente si coincide la contraseña, o null si no.
     */
    public Cliente validarCredenciales(String email, String password) {
        Optional<Cliente> optCliente = clienteRepository.findByCorreo(email);
        if (optCliente.isPresent()) {
            Cliente cliente = optCliente.get();
            if (passwordEncoder.matches(password, cliente.getPassword())) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Actualiza la dirección de envío de un cliente existente.
     * @throws EntityNotFoundException si no se encuentra el cliente.
     */
    public Cliente actualizarDireccionEnvio(Long clienteId, String nuevaDireccion) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new EntityNotFoundException(
                "No se encontró un cliente con ID " + clienteId
            ));
        cliente.setDireccionEnvio(nuevaDireccion);
        return clienteRepository.save(cliente);
    }
    public void actualizarDirrecionEnvio(Cliente cliente) {
        clienteRepository.save(cliente);
    }
}