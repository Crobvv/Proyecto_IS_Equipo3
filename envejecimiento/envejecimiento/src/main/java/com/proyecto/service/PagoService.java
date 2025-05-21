package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Pago;
import com.proyecto.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public Pago registrarPago(Pago pago) {
        pago.setEstado("PENDIENTE");
        return pagoRepository.save(pago);
    }

    public Pago confirmarPago(int idPago) {
        Pago pago = pagoRepository.findById(idPago).orElse(null);
        if (pago != null) {
            pago.setEstado("COMPLETADO");
            pagoRepository.save(pago);
        }
        return pago;
    }

    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    public Pago obtenerPorId(int idPago) {
        return pagoRepository.findById(idPago).orElse(null);
    }
}

