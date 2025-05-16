package com.proyecto.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Pago;
import com.proyecto.model.Pedido;
import com.proyecto.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido registrarPedido(Pedido pedido, Pago pago) {
        pedido.setFechaCreacion(new Date());
        pedido.getCarritoCompras().calcularTotal();
        pedido.setPago(pago);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}

