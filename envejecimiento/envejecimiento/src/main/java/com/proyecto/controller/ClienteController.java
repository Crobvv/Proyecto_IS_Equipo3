package com.proyecto.controller;

// import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto.model.Cliente;
import com.proyecto.service.ClienteService;
import com.proyecto.service.PedidoService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final PedidoService pedidoService;

    public ClienteController(ClienteService clienteService,
                             PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.pedidoService  = pedidoService;
    }

    // 1) Mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registro";
    }

        @PostMapping("/registro")
    public String registrarCliente(
        @RequestParam String nombre,
        @RequestParam String correo, 
        @RequestParam String password, 
        @RequestParam String RFC,
        @RequestParam String codigoPostal, 
        @RequestParam(required = false) String direccionEnvio) {
        
        Cliente cliente = new Cliente(nombre, correo, password, RFC, codigoPostal, direccionEnvio);
        clienteService.registrarCliente(cliente);
        return "redirect:/cliente/login";
    }

    // 2) Mostrar login
    @GetMapping("/login")
    public String mostrarLoginCliente(HttpSession session) {
        if (session.getAttribute("cliente") != null) {
            return "redirect:/";
        }
        return "login";
    }

    // 3) Procesar login
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
            return "redirect:/";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

    // 4) Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        pedidoService.cerrarSesion();
        session.invalidate();
        return "redirect:/cliente/login?logout";
    }

    // 5) Mostrar carrito
    @GetMapping("/carrito")
    public String mostrarCarrito(HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            return "redirect:/cliente/login";
        }

        var pedido = pedidoService.getPedidoActivo();
        var carrito = pedido.getCarritoCompras();

        model.addAttribute("carrito", carrito);
        model.addAttribute("items", pedidoService.listarTodos());
        model.addAttribute("total", carrito.getTotal());

        return "carrito";
    }

    // 6) Mostrar formulario para actualizar dirección
    @GetMapping("/actualizar-direccion")
    public String mostrarFormularioDireccion(HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            return "redirect:/cliente/login";
        }
        model.addAttribute("direccionActual", cliente.getDireccionEnvio());
        return "actualizar-direccion";
    }

    @PostMapping("/actualizar-direccion")
    public String actualizarDireccion(@RequestParam String direccion, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if(cliente == null) {
            return "redirect:/cliente/login";
        }
        cliente.setDireccionEnvio(direccion);
        clienteService.actualizarDirrecionEnvio(cliente);
        session.setAttribute("cliente", cliente);
        return "redirect:/"; // redirige al home

    }

}