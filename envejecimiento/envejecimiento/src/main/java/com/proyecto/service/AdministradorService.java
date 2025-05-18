package com.proyecto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.model.Administrador;
import com.proyecto.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Administrador registrarAdministrador(Administrador admin) {
        int maxId = administradorRepository.findMaxIdUsuario(); // Consultamos el idUsuario más alto entre admins

        int nextId; // Calculamos el siguiente según la progresión de 10 en 10
        if(maxId < 1000) {
            nextId = 1000;
        } else {
            nextId = maxId + 10;
        }

        admin.setIdUsuario(nextId); // Asigna al objeto antes de guardar

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        
        return administradorRepository.save(admin);
    }

    public Optional<Administrador> buscarPorCorreo(String correo) {
        return administradorRepository.findByCorreo(correo);
    }

    public Administrador obtenerPorId(Long id) {
        return administradorRepository.findById(id).orElse(null);
    }

    public boolean autenticar(String correo, String rawPassword) {
        Optional<Administrador> adminOpt = buscarPorCorreo(correo);
        return adminOpt.isPresent() && passwordEncoder.matches(rawPassword, adminOpt.get().getPassword());
    }   
}