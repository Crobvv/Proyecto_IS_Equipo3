package com.proyecto.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class ReporteVentas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReporte;
    private Date fechaGeneracion;
    private double ventasTotales;

    public void generarReporte() {}
    public void exportarDoc() {}
    public void visualizarHistorial() {}

    // Getters y setters
    
    public int getIdReporte() {
        return idReporte;
    }
    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }
    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }
    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
    public double getVentasTotales() {
        return ventasTotales;
    }
    public void setVentasTotales(double ventasTotales) {
        this.ventasTotales = ventasTotales;
    }
        
}
