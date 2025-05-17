package com.proyecto.service;

import com.proyecto.model.ReporteVentas;
import com.proyecto.repository.ReporteVentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteVentasService {

    @Autowired
    private ReporteVentasRepository reporteVentasRepository;

    public List<ReporteVentas> listarReportes() {
        return reporteVentasRepository.findAll();
    }

    public Optional<ReporteVentas> obtenerPorId(int id) {
        return reporteVentasRepository.findById(id);
    }

    public ReporteVentas guardar(ReporteVentas reporte) {
        return reporteVentasRepository.save(reporte);
    }

    public void eliminar(int id) {
        reporteVentasRepository.deleteById(id);
    }
}
