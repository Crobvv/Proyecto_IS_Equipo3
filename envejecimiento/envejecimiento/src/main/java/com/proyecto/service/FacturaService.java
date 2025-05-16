package com.proyecto.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Factura;
import com.proyecto.repository.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public Factura generarFactura(Factura factura) {
        factura.setFechaEmision(new Date());
        factura.setActividad("Generada automáticamente");
        return facturaRepository.save(factura);
    }

    public List<Factura> listarTodas() {
        return facturaRepository.findAll();
    }

    public Factura obtenerPorId(int id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public List<Factura> buscarPorCliente(Long clienteId) {
        return facturaRepository.findByClienteId(clienteId);
    }
}

