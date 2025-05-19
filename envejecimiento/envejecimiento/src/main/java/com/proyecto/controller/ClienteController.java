package com.proyecto.controller;

import com.proyecto.model.Cliente;
import com.proyecto.service.ClienteService;
import com.proyecto.service.PedidoService;
import com.proyecto.service.ProductoService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ProductoService productoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoService pedidoService;

    ClienteController(ProductoService productoService) {
        this.productoService = productoService;
    } // Inyectamos el servicio

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registro";
    }
    
    @PostMapping("/registro")
    public String registrarCliente(@ModelAttribute Cliente cliente) {
        clienteService.registrarCliente(cliente);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLoginCliente() {
        return "login";
    }

    @PostMapping("/login")
    public String verificarCredenciales(@RequestParam String email, @RequestParam String password, HttpSession session,  Model model) {
        Cliente cliente = clienteService.validarCredenciales(email, password);
        if(cliente != null) {
            session.setAttribute("cliente", cliente);
            pedidoService.iniciarPedido(cliente);
            return "redirect:/cliente/panel";
        } else {
            model.addAttribute("error", "Correo o constraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/panel")
    public String mostrarPanelCliente(HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if(cliente == null) {
            return "redirect:/cliente/login";
        }

        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("cliente", cliente);
        model.addAttribute("carrito", pedidoService.getPedidoActivo().getCarritoCompras().getCantidadProductos());
        return "panel";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        pedidoService.cerrarSesion(); // Guarda y limpia el pedido activo
        session.invalidate(); // Invalida la sesión entera
        return "redirect:/cliente/login?logout";
    }
}