package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.Cliente;
import com.proyecto.model.Pago;
import com.proyecto.model.Pedido;
import com.proyecto.service.PagoService;
import com.proyecto.service.PedidoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/formulario")
    public String mostrarFormularioPago(HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if(cliente == null) {
            return "redirect:/cliente/login";
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("carrito", pedidoService.verCarrito());
        model.addAttribute("pago", new Pago());

        return "pago";
    }

    @PostMapping("/formulario")
    public String procesarPago(@ModelAttribute Pago pagoFormulario, HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if(cliente == null) {
            return "redirect:/cliente/login";
        }

        try {
            // Registramos el pago como "PENDIENTE"
            Pago pagoRegistrado = pagoService.registrarPago(pagoFormulario); 

            // Confirmamos el pedido con el pago registrado
            Pedido pedidoConfirmado = pedidoService.confirmarPedido(pagoRegistrado, "Pago realizado con éxito");

            // Confirmmamos el pago como "COMPLETADO"
            pagoService.confirmarPago(pagoRegistrado.getIdPago());
        
            model.addAttribute("cliente", cliente);
            model.addAttribute("pedido", pedidoConfirmado);

            session.setAttribute("pedidoConfirmado", pedidoConfirmado);

            return "confirmacion-pedido";
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un error al procesar el pago: " + e.getMessage());
            return("/formulario");
        }
    }

    @GetMapping("/confirmacion-pedido")
    public String mostrarConfirmacionPedido(HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (cliente == null) {
            return "redirect:/cliente/login";
        }

        Pedido pedido = (Pedido) session.getAttribute("pedidoConfirmado");

        if (pedido == null) {
            model.addAttribute("mensaje", "No hay un pedido confirmado para mostrar.");
            return "redirect:/pedido/carrito/vista"; // o puedes ir a una vista personalizada de error
        }

        model.addAttribute("cliente", cliente);
        model.addAttribute("pedido", pedido);
        model.addAttribute("pago", pedido.getPago());

        return "confirmacion-pedido";
    }

    @PostMapping
    public ResponseEntity<Pago> registrar(@RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.registrarPago(pago));
    }

    @PutMapping("/confirmar/{id}")
    public ResponseEntity<Pago> confirmarPago(@PathVariable int id) {
        Pago confirmado = pagoService.confirmarPago(id);
        return confirmado != null ? ResponseEntity.ok(confirmado) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listarTodos() {
        return ResponseEntity.ok(pagoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPorId(@PathVariable int id) {
        Pago pago = pagoService.obtenerPorId(id);
        return pago != null ? ResponseEntity.ok(pago) : ResponseEntity.notFound().build();
    }
}
