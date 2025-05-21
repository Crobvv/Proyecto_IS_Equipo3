package com.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;

    private double monto;
    
    private String metodo;
    private String estado;

    @Transient
    private String numeroTarjeta;
    @Transient
    private String nombreTitular;
    @Transient
    private String mesExp;
    @Transient
    private String anoExp;
    @Transient
    private String cvv;
    @Transient
    private boolean facturaMisma;

    public Pago() {
        // Constructor vacío requerido por JPA
    }

    public void procesarPago() {}
    public void confirmarPago() {}

    // Getters y setters
    public int getIdPago() { return idPago; }
    public double getMonto() { return monto; }
    public String getMetodo() { return metodo; }
    public String getEstado() { return estado; }
    public String getNumeroTarjeta() { return numeroTarjeta; }
    public String getNombreTitular() { return nombreTitular; }
    public String getMesExp() { return mesExp; }
    public String getAnoExp() { return anoExp; }
    public String getCvv() { return cvv; }
    public boolean isFacturaMisma() { return facturaMisma; }

    public void setIdPago(int idPago) { this.idPago = idPago; }
    public void setMonto(double monto) { this.monto = monto; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }
    public void setNombreTitular(String nombreTitular) { this.nombreTitular = nombreTitular; }
    public void setMesExp(String mesExp) { this.mesExp = mesExp; }
    public void setAnoExp(String anoExp) { this.anoExp = anoExp; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public void setFacturaMisma(boolean facturaMisma) { this.facturaMisma = facturaMisma; }
}