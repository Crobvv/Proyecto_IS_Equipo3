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
        // Validamos la clave de acceso
        if(admin.getAccessKey() == null || !admin.getAccessKey().equals("210525")) {
            throw new IllegalArgumentException("Clave de acceso inválida. Registro denegado.");
        }
        
        int maxId = administradorRepository.findMaxIdUsuario(); // Consultamos el idUsuario más alto entre admins
        int nextId = (maxId < 1000) ? 1000 : maxId + 10; // Calculamos el siguiente según la progresión de 10 en 10
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

    public Administrador login(String correo, String rawPassword, String accessKey) {
        Optional<Administrador> adminOpt = buscarPorCorreo(correo);

        if(adminOpt.isPresent()) {
            Administrador admin = adminOpt.get();

            boolean passwordValida = passwordEncoder.matches(rawPassword, admin.getPassword());
            boolean claveCorrecta = accessKey != null && accessKey.equals("210525");

            if(passwordValida && claveCorrecta) {
                return admin;
            }
        }
        return null;
    }
}