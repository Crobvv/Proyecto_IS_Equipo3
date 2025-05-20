package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.proyecto.model.Cliente;
import com.proyecto.model.Producto;
import com.proyecto.service.PedidoService;
import com.proyecto.service.ProductoService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Producto> registrar(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.registrarProducto(producto));
    }
    
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable int id) {
        Producto producto = productoService.obtenerPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producto/{idProducto}")
    public String verDetalle(@PathVariable("idProducto") Integer idProducto, Model model) {
        Producto prod = productoService.obtenerPorId(idProducto);
        model.addAttribute("producto", prod);
        return "productoDetalle";
    }
    
    
    @PostMapping("/producto/agregar-al-carrito")
    public RedirectView agregarAlCarrito(@RequestParam("idProducto") int idProducto,
        @RequestParam("cantidad") int cantidad, HttpSession session, Model model) {
    
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            return new RedirectView("/cliente/login");
        }

        // Asegurarse de que el pedido esté iniciado
        pedidoService.iniciarPedido(cliente);

        // Agregar el producto la cantidad de veces solicitada
        for (int i = 0; i < cantidad; i++) {
            pedidoService.agregarProductoAlCarrito(idProducto);
        }
        return new RedirectView("/pedido/carrito/vista");
    }
    
}