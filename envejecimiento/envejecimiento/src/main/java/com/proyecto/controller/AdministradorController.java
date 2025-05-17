package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.Administrador;
import com.proyecto.service.AdministradorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/admin")
public class AdministradorController {
    
    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "registro/admin";
    }

    @PostMapping("/registro")
    public String registrarAdministrador(@ModelAttribute Administrador administrador) {
        administradorService.registrarAdministrador(administrador);
        return "redirect:/login?admin";
    }

    @GetMapping("/panel")
    public String mostrarPanelAdmin() {
        return "admin/panel";
    }
}