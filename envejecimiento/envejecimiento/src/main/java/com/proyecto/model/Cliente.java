package com.proyecto.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // este ID es necesario para JPA aunque herede de Usuario (revisamos eso abajo)

    @Column(nullable = false, unique = true)
    private String RFC;

    private String codigoPostal;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public Cliente() {
        // Constructor vacío requerido por JPA
    }

    public void Cliente(int idUsuario, String nombre, String correo, String password) {
        this.setIdUsuario(idUsuario);
        this.setNombre(nombre);
        this.setCorreo(correo);
        this.setPassword(password);
    }

    public Cliente(int idUsuario, String nombre, String correo, String password, String RFC, String codigoPostal) {
        Cliente(idUsuario, nombre, correo, password);
        this.RFC = RFC;
        this.codigoPostal = codigoPostal;
    }

    // Métodos adicionales
    public void consultarPerfil() {
        System.out.println("Perfil de cliente: " + getNombre() + " - RFC: " + RFC);
    }

    public void historialCompras() {
        System.out.println(getNombre() + " ha confirmado un pedido.");
    }

    // Getters y setters
    public String getRFC() { return RFC; }
    public void setRFC(String RFC) { this.RFC = RFC; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public List<Factura> getFacturas() { return facturas; }
    public void setFacturas(List<Factura> facturas) { this.facturas = facturas; }

    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }

    @Override
    public void iniciarSesion() {
        System.out.println("Inicio de sesión para cliente: " + getNombre());
    }

    @Override
    public void cerrarSesion() {
        System.out.println("Cierre de sesión para cliente: " + getNombre());
    }
}