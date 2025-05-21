package com.proyecto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;

@Entity
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private String estado;      // "activo", "confirmado"
    private Date fechaCreacion;
    private Date fechaEntrega;
    private String detalles;
    
    private double total;

    @ManyToMany (cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
        name = "pedido_producto",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos = new ArrayList<>();

    @Transient
    private CarritoCompras carritoCompras;

    @OneToOne(cascade = CascadeType.ALL)
    private Pago pago;

    @ManyToOne
    private Cliente cliente;

    // Incializa pedido y su carrito interno con estado activo
    public Pedido() {
        this.carritoCompras = new CarritoCompras();
        this.estado = "ACTIVO";
        this.fechaCreacion = new Date();
    }

    // Callback JPA: tras cargar de BD, reconstruye el carrito interno desde la lista persistida.
    @PostLoad
    private void cargarCarritoDesdeProductos() {
        this.carritoCompras = new CarritoCompras();
        for(Producto p : this.productos) {
            carritoCompras.agregarProducto(p);
        }
        this.carritoCompras.setTotal(this.total);
    }

    // Guarda el estado actual desl carrito en los campos persistidos
    // Se debe llamar antes de cerrar sesión o al guardar cambios
    public void guardarCarritoEnPedido() {
        this.productos = new ArrayList<>(carritoCompras.getProductos());
        carritoCompras.calcularTotal();
        this.total = carritoCompras.getTotal();
    }

    // Método para trabajar con carrito y luego confirmar
    public void confirmarPedido() {
        guardarCarritoEnPedido();
        this.estado = "CONFIRMADO";
        this.fechaCreacion = new Date();
        carritoCompras.vaciarCarrito();
    }

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
        if(carritoCompras == null) {
            cargarCarritoDesdeProductos();
        }
        return carritoCompras;
    }
    public void setCarritoCompras(CarritoCompras carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
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