package com.proyecto.controller;

import com.proyecto.model.Categoria;
import com.proyecto.model.Festividad;
import com.proyecto.model.Producto;
import com.proyecto.service.ProductoService;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductoController {

    private final ProductoService productoService;

    // @Autowired
    public AdminProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "admin_productos";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", Categoria.values());
        model.addAttribute("festividades", Festividad.values());
        return "admin_nuevo_producto";
    }

    @PostMapping
    public String guardar(@ModelAttribute Producto producto) {
        productoService.registrarProducto(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Producto prod = productoService.obtenerPorId(id);
        model.addAttribute("producto", prod);
        model.addAttribute("categorias", Categoria.values());
        model.addAttribute("festividades", Festividad.values());
        return "admin_editar_producto";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Producto producto) {
        producto.setIdProducto(id);
        productoService.save(producto);
        return "redirect:/admin/productos";
    }

    // Mostrar confirmación de eliminación
    @GetMapping("/eliminar/{id}")
    public String confirmarEliminacion(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productoService.obtenerPorId(id));
        return "admin_eliminar_producto";
    }

    // Procesar borrado
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        productoService.eliminarProducto(id);
        ra.addFlashAttribute("exito", "Producto eliminado correctamente");
        return "redirect:/admin/productos";
    }
}
