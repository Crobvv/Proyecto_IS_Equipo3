package com.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.ReporteVentas;
import com.proyecto.service.ReporteVentasService;

@RestController
@RequestMapping("/api/reportes")
public class ReporteVentasController {

    @Autowired
    private ReporteVentasService reporteVentasService;

    @GetMapping
    public List<ReporteVentas> listarReportes() {
        return reporteVentasService.listarReportes();
    }

    @GetMapping("/{id}")
    public Optional<ReporteVentas> obtenerPorId(@PathVariable int id) {
        return reporteVentasService.obtenerPorId(id);
    }

    @PostMapping
    public ReporteVentas guardar(@RequestBody ReporteVentas reporte) {
        return reporteVentasService.guardar(reporte);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        reporteVentasService.eliminar(id);
    }
}