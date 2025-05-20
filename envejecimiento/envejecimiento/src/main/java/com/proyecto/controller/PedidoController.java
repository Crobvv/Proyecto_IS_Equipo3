package com.proyecto.controller;

import com.proyecto.model.Cliente;
import com.proyecto.model.Pago;
import com.proyecto.model.Pedido;
import com.proyecto.service.PedidoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Vista Thymeleaf para ver el carrito
    @GetMapping("/carrito/vista")
    public String verCarritoVista(HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            return "redirect:/cliente/login";
        }
        pedidoService.iniciarPedido(cliente); // <-- Aquí aseguras que el pedidoActivo esté listo

        model.addAttribute("cliente", cliente);
        model.addAttribute("carrito", pedidoService.verCarrito());
        return "carrito"; // Renderiza carrito.html
    }

    // Agrega un producto al carrito del pedido activo (API JSON)
    @PostMapping("/carrito/{productoId}")
    @ResponseBody
    public ResponseEntity<Void> agregarProductoCarrito(@PathVariable int productoId) {
        pedidoService.agregarProductoAlCarrito(productoId);
        return ResponseEntity.ok().build();
    }

    // Elimina un producto del carrito del pedido activo (API JSON)
    @DeleteMapping("/carrito/eliminar/{productoId}")
    @ResponseBody
    public ResponseEntity<Void> eliminarProductoCarrito(@PathVariable int productoId) {
        pedidoService.eliminarProductoDelCarrito(productoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/carrito/eliminar/{productoId}")
    public String eliminarProductoDesdeVista(@PathVariable int productoId, HttpSession session, Model model) {
        pedidoService.eliminarProductoDelCarrito(productoId);
        return "redirect:/pedido/carrito/vista"; // Redirige al carrito visual nuevamente
    }

    // Devuelve los datos del carrito en formato JSON (API JSON)
    @GetMapping("/carrito")
    @ResponseBody
    public ResponseEntity<?> verCarritoJson() {
        return ResponseEntity.ok(pedidoService.verCarrito());
    }

    // Confirma el pedido activo con pago y detalles (API JSON)
    @PostMapping("/confirmar")
    @ResponseBody
    public ResponseEntity<Pedido> confirmarPedido(@RequestBody ConfirmacionRequest request) {
        Pedido pedidoConfirmado = pedidoService.confirmarPedido(request.getPago(), request.getDetalles());
        return ResponseEntity.ok(pedidoConfirmado);
    }

    // Te lleva a la página de confirmar pedido
    @GetMapping("/confirmar")
    public String pantallaDePago(HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            return "redirect:/cliente/login";
        }
        pedidoService.iniciarPedido(cliente); // <-- Aquí aseguras que el pedidoActivo esté listo

        model.addAttribute("cliente", cliente);
        model.addAttribute("carrito", pedidoService.verCarrito());
        return "pago";
    }

    // Listar todos los pedidos confirmados (API JSON)
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    // Obtener pedido por ID (API JSON)
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    // Listar pedidos de un cliente (API JSON)
    @GetMapping("/cliente/{clienteId}")
    @ResponseBody
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
