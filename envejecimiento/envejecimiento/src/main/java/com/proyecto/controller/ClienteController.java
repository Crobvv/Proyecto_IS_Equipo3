package com.proyecto.controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.model.Cliente;
import com.proyecto.service.ClienteService;
import com.proyecto.service.PedidoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final PedidoService pedidoService;

    // @Autowired
    public ClienteController(ClienteService clienteService,
                             PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.pedidoService  = pedidoService;
    }

    // 1) Registro → al terminar, lleva a /cliente/login
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarCliente(@ModelAttribute Cliente cliente) {
        clienteService.registrarCliente(cliente);
        return "redirect:/cliente/login";
    }

    // 2) Login GET → si ya hay sesión, va al home principal
    @GetMapping("/login")
    public String mostrarLoginCliente(HttpSession session) {
        if (session.getAttribute("cliente") != null) {
            return "redirect:/";    // tu “main”
        }
        return "login";
    }

    // 3) Login POST → al validar, redirige a main ("/")
    @PostMapping("/login")
    public String verificarCredenciales(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Cliente cliente = clienteService.validarCredenciales(email, password);
        if (cliente != null) {
            session.setAttribute("cliente", cliente);
            pedidoService.iniciarPedido(cliente);
            return "redirect:/";    // de nuevo al “main”
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

    // 4) Logout → limpia sesión y vuelve al login
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        pedidoService.cerrarSesion();
        session.invalidate();
        return "redirect:/cliente/login?logout";
    }

    @GetMapping("/carrito")
        public String mostrarCarrito(HttpSession session, Model model) {
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            if (cliente == null) {
                return "redirect:/cliente/login";
            }

            var pedido = pedidoService.getPedidoActivo();
            var carritoCompras = pedido.getCarritoCompras();

            model.addAttribute("carrito", carritoCompras);
            model.addAttribute("items", pedidoService.listarTodos());
            model.addAttribute("total", carritoCompras.getTotal());

            return "carrito";
        }
}