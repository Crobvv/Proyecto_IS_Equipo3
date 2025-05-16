import java.util.ArrayList;
import java.util.List;

public class CarritoCompras {

    // Atributos privados
    private List<Producto> productos;
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
        System.out.println("Producto agregado: " + p.getNombre());
    }

    public void eliminarProducto(Producto p) {
        if (productos.remove(p)) {
            calcularTotal();
            System.out.println("Producto eliminado: " + p.getNombre());
        } else {
            System.out.println("El producto no se encuentra en el carrito.");
        }
    }

    public void calcularTotal() {
        total = productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public void vaciarCarrito() {
        productos.clear();
        total = 0.0;
        System.out.println("Carrito vaciado.");
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
