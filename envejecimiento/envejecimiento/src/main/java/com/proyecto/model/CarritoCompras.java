package com.proyecto.model;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompras {

    private List<Producto> productos = new ArrayList<>();
    private double total;

    // Constructor
    public CarritoCompras() {
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    // Métodos del diagrama detallado de clases
   public void agregarProducto(Producto p) {
        productos.add(p);
        calcularTotal();
    }

    public void eliminarProducto(Producto p) {
        productos.remove(p);
        calcularTotal();
    }

    public int getCantidadProductos() {
        return productos.size();
    }

    public void calcularTotal() {
        total = productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public void vaciarCarrito() {
        productos.clear();
        total = 0.0;
    }

    // Getters y setter
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

    public Producto getProductoDelCarrito(int idProducto) {
        for(Producto p : productos) {
            if(p.getIdProducto() == idProducto) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "CarritoCompras [productos=" + productos + ", total=" + total + "]";
    }
}