package com.proyecto.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.model.Producto;
import com.proyecto.service.ProductoService;

@Controller
public class HomeController {

    private final ProductoService productoService;

    public HomeController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping({"/", "/home"})
    public String home(
        @RequestParam(value="search",     required=false) String search,
        @RequestParam(value="category",   required=false) String category,
        @RequestParam(value="festividad", required=false) String festividad,
        Model model
    ) {
        List<Producto> productos;
        String mensaje;

        if (search != null && !search.isBlank()) {
            productos = productoService.buscarPorNombre(search);
            mensaje = "Resultados para \"" + search + "\"";
        }
        else if (category != null && !category.isBlank()) {
            productos = productoService.buscarPorCategoria(category);
            mensaje = "Categoría: " + category;
        }
        else if (festividad != null && !festividad.isBlank()) {
            productos = productoService.buscarPorFestividad(festividad);
            mensaje = "Festividad: " + festividad;
        }
        else {
            productos = productoService.listarTodos();
            mensaje = "¡Bienvenidos a nuestro sistema de ventas!";
        }

        model.addAttribute("productos", productos);
        model.addAttribute("mensaje", mensaje);
        return "home";
    }

    @GetMapping("/login")
    public String mostrarLoginCliente() {
        return "login";
    }

    @GetMapping("/masProductos")
    public String verTodosProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "mas_Productos";
    }

    @GetMapping("/producto/{idProducto}")
    public String verDetalleProducto(
        @PathVariable("idProducto") int idProducto,
        Model model
    ) {
        Producto prod = productoService.obtenerPorId(idProducto);
        model.addAttribute("producto", prod);
        return "productoDetalle";
    }
}