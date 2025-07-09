package com.empresa.fidelidad.service;

import com.empresa.fidelidad.model.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteService {
    private Map<String, Cliente> clientes = new HashMap<>();

    public Cliente agregarCliente(String id, String nombre, String correo) {
        Cliente cliente = new Cliente(id, nombre, correo);
        clientes.put(id, cliente);
        return cliente;
    }

    public void actualizarCliente(String id, String nuevoNombre, String nuevoCorreo) {
        Cliente cliente = clientes.get(id);
        if (cliente != null) {
            cliente.setNombre(nuevoNombre);
            cliente.setCorreo(nuevoCorreo);
        }
    }

    public void eliminarCliente(String id) {
        clientes.remove(id);
    }

    public Cliente obtenerCliente(String id) {
        return clientes.get(id);
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }
}
