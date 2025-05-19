package com.proyecto.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.proyecto.service.ProductoService;

@Component
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Festividad festividad;
    
    @Column(name = "ruta_imagen", length = 255)
    private String rutaImagen;

    // Servicio inyectado para guardar instancias
    private static ProductoService productoService;

    @Autowired
    public void setProductoService(ProductoService service) {
        Producto.productoService = service;
    }

    public Producto() {
        // Constructor sin argumentos requerido por JPA
    }

    // Constructor con todos los campos
    public Producto(int idProducto, String nombre, double precio, int stock,
                    Categoria categoria, Festividad festividad, String rutaImagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.festividad = festividad;
        this.rutaImagen = rutaImagen;
    }

    /**
     * Guarda o actualiza este producto en la base de datos.
     * @return la instancia persistida con ID actualizado.
     */
    public Producto save() {
        if (productoService == null) {
            throw new IllegalStateException("ProductoService no ha sido inicializado");
        }
        return productoService.save(this);
    }

    // Métodos existentes (actualizarStock, consultarDetalles, getters/setters)...

    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
        System.out.println("Stock actualizado. Nuevo stock de " + nombre + ": " + stock);
    }

    public void consultarDetalles() {
        System.out.println("Producto:           " + nombre);
        System.out.println("ID:                  " + idProducto);
        System.out.println("Precio:             $" + precio);
        System.out.println("Stock:               " + stock);
        System.out.println("Categoría:          " + (categoria != null ? categoria.name() : "Sin categoría"));
        System.out.println("Festividad:         " + (festividad != null ? festividad.name() : "Sin festividad"));
        System.out.println("Ruta de imagen:     " + rutaImagen);
    }

    // Getters y setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Festividad getFestividad() { return festividad; }
    public void setFestividad(Festividad festividad) { this.festividad = festividad; }

    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}
