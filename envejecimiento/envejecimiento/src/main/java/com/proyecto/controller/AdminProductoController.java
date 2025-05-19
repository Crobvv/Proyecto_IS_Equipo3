package com.proyecto.controller;

import com.proyecto.model.Categoria;
import com.proyecto.model.Festividad;
import com.proyecto.model.Producto;
import com.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "admin_productos";   // coincide con admin_productos.html en /templates
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model m) {
        m.addAttribute("producto", new Producto());
        m.addAttribute("categorias", Categoria.values());
        m.addAttribute("festividades", Festividad.values());
    return "admin_nuevo_producto";
    }
    @PostMapping
    public String guardar(@ModelAttribute Producto producto) {
        productoService.registrarProducto(producto);
    return "redirect:/admin/productos";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable int id, Model model) {
        Producto prod = productoService.obtenerPorId(id);
        model.addAttribute("producto", prod);
        model.addAttribute("categorias", Categoria.values());
        model.addAttribute("festividades", Festividad.values());
        return "admin_editar_producto"; 
    }

    // Procesar el envío de edición
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute Producto producto) {
        producto.setIdProducto(id);
        productoService.save(producto);
        return "redirect:/admin/productos";  // redirige al listado
    }
}