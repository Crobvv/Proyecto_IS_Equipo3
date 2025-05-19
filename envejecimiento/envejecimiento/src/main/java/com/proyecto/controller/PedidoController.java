package com.proyecto.controller;

import com.proyecto.model.Cliente;
import com.proyecto.model.Pago;
import com.proyecto.model.Pedido;
import com.proyecto.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/{api}/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Inicia o recarga pedido activo para un cliente (al hacer login)
    @PostMapping("/iniciar")
    public ResponseEntity<Void> iniciarPedido(@RequestBody Cliente cliente) {
        pedidoService.iniciarPedido(cliente);
        return ResponseEntity.ok().build();
    }

    // Agrega un producto al carrito del pedido activo
    @PostMapping("/carrito/{productoId}")
    public ResponseEntity<Void> agregarProductoCarrito(@PathVariable int productoId) {
        pedidoService.agregarProductoAlCarrito(productoId);
        return ResponseEntity.ok().build();
    }

    // Elimina un producto del carrito del pedido activo
    @DeleteMapping("/carrito/{productoId}")
    public ResponseEntity<Void> eliminarProductoCarrito(@PathVariable int productoId) {
        pedidoService.agregarProductoAlCarrito(productoId);
        return ResponseEntity.ok().build();
    }

    // Muestra el pedido activo con pago y detalles
    @GetMapping("/carrito")
    public ResponseEntity<?> verCarrito() {
        return ResponseEntity.ok(pedidoService.verCarrito());
    }
    
    // Confirma el pedido activo con pago y detalles
    @PostMapping("/confirmar")
    public ResponseEntity<Pedido> confirmarPedido(@RequestBody ConfirmacionRequest request) {
        Pedido pedidoConfirmado = pedidoService.confirmarPedido(request.getPago(), request.getDetalles());
        return ResponseEntity.ok(pedidoConfirmado);
    }

    // Cierra la sesión del cliente y guarda el carrito en el pedido activo
    @PostMapping("/cerrar-sesion")
    public ResponseEntity<Void> cerrarSesion() {
            return ResponseEntity.ok().build();
    }

    // Listar todos los pedidos confirmados
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    // Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    // Listar pedidos de un cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> pedidosPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.obtenerPedidosPorCliente(clienteId));
    }

    // DTO para la petición de confirmación
    public static class ConfirmacionRequest {
        private Pago pago;
        private String detalles;

        public Pago getPago() {
            return pago;
        }
        public void setPago(Pago pago) {
            this.pago = pago;
        }

        public String getDetalles() {
            return detalles;
        }
        public void setDetalles(String detalles) {
            this.detalles = detalles;
        }
    }
}