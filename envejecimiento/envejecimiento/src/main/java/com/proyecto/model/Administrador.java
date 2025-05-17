package com.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Administrador extends Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Administrador() {
        // Constructor vacío requerido por JPA
    } 

    public Administrador(int idUsuario, String nombre, String correo, String password) {
        this.setIdUsuario(idUsuario);
        this.setNombre(nombre);
        this.setCorreo(correo);
        this.setPassword(password);
    }

    // Métodos adicionales
    @Override
    public void iniciarSesion() {
        System.out.println("Incio de sesión para administrador: " + getNombre());
    }

    @Override
    public void cerrarSesion() {
        System.out.println("Cierre de sesión para administrador: " + getNombre());
    }
}
