package com.proyecto.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    private String estado;
    private Date fechaCreacion;
    private Date fechaEntrega;
    private String detalles;

    @Embedded
    private CarritoCompras carritoCompras;

    @OneToOne(cascade = CascadeType.ALL)
    private Pago pago;

    @ManyToOne
    private Cliente cliente;

    // Métodos como consultarEstado y solicitarReembolso pueden estar en servicio
    public void consultarEstado() {
        System.out.println("Estado del pedido: " + estado);
    }
    
    public void solicitarReembolso() {
        System.out.println("Reembolso solicitado para el pedido " + idPedido);
    }

    // Getters y setters
    
    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public CarritoCompras getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoCompras carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
