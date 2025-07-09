package com.empresa.fidelidad;

import com.empresa.fidelidad.model.Cliente;
import com.empresa.fidelidad.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        clienteService = new ClienteService();
    }

    @Test
    void agregarClienteValido_debeCrearClienteConBronceY0Puntos() {
        Cliente cliente = clienteService.agregarCliente("1", "Juan", "juan@mail.com");

        assertNotNull(cliente);
        assertEquals("Juan", cliente.getNombre());
        assertEquals("juan@mail.com", cliente.getCorreo());
        assertEquals(0, cliente.getPuntos());
        assertEquals("BRONCE", cliente.getNivel().name());
    }

    @Test
    void agregarClienteCorreoInvalido_debeLanzarExcepcion() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                clienteService.agregarCliente("2", "Pedro", "pedromail.com")
        );
        assertEquals("Correo inválido", ex.getMessage());
    }

    @Test
    void actualizarCliente_debeActualizarNombreYCorreo() {
        clienteService.agregarCliente("3", "Ana", "ana@mail.com");
        clienteService.actualizarCliente("3", "Ana María", "ana.maria@mail.com");

        Cliente actualizado = clienteService.obtenerCliente("3");
        assertEquals("Ana María", actualizado.getNombre());
        assertEquals("ana.maria@mail.com", actualizado.getCorreo());
    }

    @Test
    void eliminarCliente_debeEliminarCliente() {
        clienteService.agregarCliente("4", "Luis", "luis@mail.com");
        clienteService.eliminarCliente("4");

        assertNull(clienteService.obtenerCliente("4"));
    }

    @Test
    void listarClientes_debeRetornarListaConClientes() {
        clienteService.agregarCliente("5", "Carlos", "carlos@mail.com");
        clienteService.agregarCliente("6", "Lucía", "lucia@mail.com");

        var clientes = clienteService.listarClientes();
        assertEquals(2, clientes.size());
    }
}
