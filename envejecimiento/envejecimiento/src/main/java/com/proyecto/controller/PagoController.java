package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.Cliente;
import com.proyecto.model.Pago;
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
