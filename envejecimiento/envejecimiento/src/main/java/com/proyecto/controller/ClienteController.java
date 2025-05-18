package com.proyecto.controller;

import com.proyecto.model.Cliente;
import com.proyecto.service.ClienteService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

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
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Correo o constraseña incorrectos");
            return "login";
        }
    }
}