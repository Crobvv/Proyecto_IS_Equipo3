package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.Administrador;
import com.proyecto.service.AdministradorService;
import com.proyecto.service.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private ProductoService productoService;

    /**
     * Muestra el formulario de login de administrador.
     */
    @GetMapping("/loginAdmin")
    public String mostrarLoginAdmin() {
        return "loginAdmin";
    }

    /**
     * Procesa el login de administrador y crea sesión.
     */
    @PostMapping("/loginAdmin")
    public String procesarLoginAdmin(
            @ModelAttribute Administrador administrador,
            HttpSession session,
            Model model) {

        Administrador adminAutenticado = administradorService.login(
            administrador.getCorreo(),
            administrador.getPassword(),
            administrador.getAccessKey()
        );

        if (adminAutenticado != null) {
            // Guardar en sesión
            session.setAttribute("adminSession", adminAutenticado);
            return "redirect:/admin/productos";
        } else {
            model.addAttribute("error", "Credenciales o clave de acceso inválidas.");
            return "loginAdmin";
        }
    }

    /**
     * Muestra el formulario de registro de nuevo administrador.
     */
    @GetMapping("/registroAdmin")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "registroAdmin";
    }

    /**
     * Procesa el registro de un nuevo administrador.
     */
    @PostMapping("/registroAdmin")
    public String registrarAdmin(
            @ModelAttribute Administrador administrador,
            Model model) {
        try {
            administradorService.registrarAdministrador(administrador);
            // Redirigir al login de administrador tras registro
            return "redirect:/admin/loginAdmin";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registroAdmin";
        }
    }
    /**
     * Cierra la sesión de administrador.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/loginAdmin";
    }
}
