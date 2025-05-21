package com.proyecto.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactura;

    private double montoTotal;
    private Date fechaEmision;
    private String actividad;

    @ManyToOne
    private Cliente cliente;

    public Factura(int idFactura, double montoTotal, Date fechaEmision, String actividad) {
        this.idFactura = idFactura;
        this.montoTotal = montoTotal;
        this.fechaEmision = fechaEmision;
        this.actividad = actividad;
    }

    public void generarFactura() {}
    public void enviarFactura() {}
    public void consultarFactura() {}

    // Getters y setters
    
    public int getIdFactura() {
        return idFactura;
    }
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    public double getMontoTotal() {
        return montoTotal;
    }
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
    public Date getFechaEmision() {
        return fechaEmision;
    }
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    public String getActividad() {
        return actividad;
    }
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}