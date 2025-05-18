package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.proyecto.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final ProductoService productoService;

    public HomeController(ProductoService productoService) {
        this.productoService = productoService;
    }
    
    @GetMapping({"/", "/home"})
    public String mostrarHome(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("mensaje", "¡Bienvenidos a nuestro sistema de ventas!");

        return "home";
    }   

    @GetMapping("/login")
    public String mostrarLoginCliente() {
        return "login";
    }
}