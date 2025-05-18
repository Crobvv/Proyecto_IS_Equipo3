package com.proyecto.controller;

import com.proyecto.model.Pedido;
import com.proyecto.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Listar todos los pedidos
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
}