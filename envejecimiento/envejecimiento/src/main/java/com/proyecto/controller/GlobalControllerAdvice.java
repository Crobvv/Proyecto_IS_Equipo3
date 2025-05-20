package com.proyecto.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.proyecto.model.Cliente;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Cada vez que se renderice una vista Thymeleaf,
     * busca en sesión el atributo "cliente" y lo expone al modelo
     * bajo la misma clave "cliente".
     */
    @ModelAttribute("cliente")
    public Cliente populateCliente(HttpSession session) {
        return (Cliente) session.getAttribute("cliente");
    }
}