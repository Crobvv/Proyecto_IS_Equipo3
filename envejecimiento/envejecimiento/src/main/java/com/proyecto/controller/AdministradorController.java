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

    @GetMapping("/loginAdmin")
    public String mostrarLoginAdmin() {
        return "loginAdmin";
    }

    @PostMapping("/loginAdmin")
    public String procesarLoginAdmin(@ModelAttribute Administrador administrador, Model model) {
        Administrador adminAutenticado = administradorService.login(
            administrador.getCorreo(),
            administrador.getPassword(), 
            administrador.getAccessKey()
        );

        if(adminAutenticado != null) {
            return "redirect:/admin/panelAdmin";
        } else {
            model.addAttribute("error", "Credenciales o clave de acceso inválidas.");
            return "loginAdmin";
        }
    }

    @GetMapping("/registroAdmin")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "registroAdmin";
    }

    @PostMapping("/registroAdmin")
    public String registrarAdmin(@ModelAttribute Administrador administrador, Model model) {
        try {
            administradorService.registrarAdministrador(administrador);   
            return "redirect:/login?admin";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registroAdmin";
        }
    }

    @GetMapping("/panelAdmin")
    public String mostrarPanelAdmin() {
        return "panelAdmin";
    }
}