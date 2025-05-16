package com.proyecto.model;

import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    public Producto() {}

    public Producto(int idProducto, String nombre, double precio, int stock, String categoria) {
      this.idProducto = idProducto;
      this.nombre = nombre;
      this.precio = precio;
      this.stock = stock;
      this.categoria = categoria;
    }

    public void actualizarStock(int cantidad) {
      this.stock += cantidad;
      System.out.println("Stock actualizado. Nuevo stock de " + nombre + ": " + stock);
    }

  public void consultarDetalles() {
      System.out.println("Producto: " + nombre);
      System.out.println("ID: " + idProducto);
      System.out.println("Precio: $" + precio);
      System.out.println("Stock: " + stock);
      System.out.println("Categoría: " + categoria);
  }
  
  public int getIdProducto() { return idProducto; }
  public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public double getPrecio() { return precio; }
  public void setPrecio(double precio) { this.precio = precio; }

  public int getStock() { return stock; }
  public void setStock(int stock) { this.stock = stock; }

  public String getCategoria() { return categoria; }
  public void setCategoria(String categoria) { this.categoria = categoria; }
}