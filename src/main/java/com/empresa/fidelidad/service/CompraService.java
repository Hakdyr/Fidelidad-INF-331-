package com.empresa.fidelidad.service;

import com.empresa.fidelidad.model.Cliente;
import com.empresa.fidelidad.model.Compra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompraService {
    private Map<String, Compra> compras = new HashMap<>();
    private ClienteService clienteService;

    public CompraService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Compra registrarCompra(String idCompra, String idCliente, double monto, LocalDate fecha) {
        Cliente cliente = clienteService.obtenerCliente(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }

        int puntosBase = (int) (monto / 100);
        int puntosTotales = (int) (puntosBase * cliente.getNivel().getMultiplicador());

        // Bonus por tercera compra en el mismo día
        long comprasHoy = cliente.getCompras().stream()
                .filter(c -> c.getFecha().equals(fecha))
                .count();
        if (comprasHoy == 2) { // Es la tercera compra
            puntosTotales += 10;
        }

        cliente.setPuntos(cliente.getPuntos() + puntosTotales);
        cliente.actualizarNivel();

        Compra compra = new Compra(idCompra, idCliente, monto, fecha);
        compras.put(idCompra, compra);
        cliente.agregarCompra(compra);

        return compra;
    }

    public Compra obtenerCompra(String idCompra) {
        return compras.get(idCompra);
    }

    public void eliminarCompra(String idCompra) {
        compras.remove(idCompra);
    }

    public List<Compra> listarCompras() {
        return new ArrayList<>(compras.values());
    }
}
