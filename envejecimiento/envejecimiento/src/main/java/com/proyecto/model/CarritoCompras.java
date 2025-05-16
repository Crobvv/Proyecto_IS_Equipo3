package com.proyecto.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToMany;


@Embeddable
public class CarritoCompras {

    @ManyToMany
    private List<Producto> productos = new ArrayList<>();

    private double total;

    // Constructor
    public CarritoCompras() {
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    // Métodos del diagrama
   public void agregarProducto(Producto p) {
        productos.add(p);
        calcularTotal();
    }

    public void eliminarProducto(Producto p) {
        productos.remove(p);
        calcularTotal();
    }

    public void calcularTotal() {
        total = productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public void vaciarCarrito() {
        productos.clear();
        total = 0.0;
    }

    // Getters
    public List<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "CarritoCompras [productos=" + productos + ", total=" + total + "]";
    }
}