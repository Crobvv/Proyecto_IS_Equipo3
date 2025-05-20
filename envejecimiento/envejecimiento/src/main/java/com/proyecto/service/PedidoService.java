package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.proyecto.model.CarritoCompras;
import com.proyecto.model.Cliente;
import com.proyecto.model.Pago;
import com.proyecto.model.Pedido;
import com.proyecto.model.Producto;
import com.proyecto.repository.PedidoRepository;
import com.proyecto.repository.ProductoRepository;

@Service
@SessionScope
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    private Pedido pedidoActivo;

    // Inicia o recarga el pedido activo al iniciar sesión
    // Busca un pedido en estado "activo" en BD, si no, crea uno
    public void iniciarPedido(Cliente cliente) {
        Optional<Pedido> encontrado = pedidoRepository.findByClienteIdAndEstado(cliente.getId(), "ACTIVO");
        if(encontrado.isPresent()) {
            this.pedidoActivo = encontrado.get(); // Aquí @PostLoad reconstruye el carritoInterno
        } else {
            this.pedidoActivo = new Pedido();
            this.pedidoActivo.setCliente(cliente);
            pedidoRepository.save(this.pedidoActivo);
        }
    }

    // Devuelve el pedido en curso (sin confirmar)
    public Pedido getPedidoActivo() {
        if(pedidoActivo == null) {
            throw new IllegalStateException("No hay un pedido activo. Llama primero a iniciarPedido()");
        }
        return pedidoActivo;
    }


    public void agregarProductoAlCarrito(int productoId) {
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        getPedidoActivo().getCarritoCompras().agregarProducto(producto);

        pedidoActivo.guardarCarritoEnPedido();
        pedidoRepository.save(pedidoActivo);
    }

    public void eliminarProductoDelCarrito(int productoId) {
        // Busca el producto en el carrito (no en el repositorio)
        Producto productoEnCarrito = getPedidoActivo().getCarritoCompras().getProductoDelCarrito(productoId);

        if (productoEnCarrito != null) {
            getPedidoActivo().getCarritoCompras().eliminarProducto(productoEnCarrito);
            pedidoActivo.guardarCarritoEnPedido();
            pedidoRepository.save(pedidoActivo);
        } else {
            throw new RuntimeException("Producto no encontrado en el carrito");
        }
    }

    public CarritoCompras verCarrito() {
        return getPedidoActivo().getCarritoCompras();
    }


    public Pedido confirmarPedido(Pago pago, String detalles) {
        Pedido activo = getPedidoActivo();
        if(activo.getCarritoCompras().getProductos().isEmpty()) {
            throw new RuntimeException("El carrito está vacío. No se puede confirmar el pedido.");
        }
        activo.setPago(pago);
        activo.setDetalles(detalles);
        activo.confirmarPedido();
        Pedido guardado = pedidoRepository.save(activo);
        // Inicia nuevo pedido para la sesión
        iniciarPedido(activo.getCliente());
        return guardado;
    }

    // Al cerrar sesión: guarda el carrito actual en BD para no perder datos.
    public void cerrarSesion() {
        if(pedidoActivo != null) {
            pedidoActivo.guardarCarritoEnPedido();
            pedidoRepository.save(pedidoActivo);
        }
        pedidoActivo = null;
    }

    public List<Pedido> obtenerPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}