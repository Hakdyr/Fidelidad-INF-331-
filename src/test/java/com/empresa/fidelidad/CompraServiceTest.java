package com.empresa.fidelidad;

import com.empresa.fidelidad.model.Cliente;
import com.empresa.fidelidad.model.Compra;
import com.empresa.fidelidad.service.ClienteService;
import com.empresa.fidelidad.service.CompraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompraServiceTest {

    private ClienteService clienteService;
    private CompraService compraService;

    @BeforeEach
    void setUp() {
        clienteService = new ClienteService();
        compraService = new CompraService(clienteService);
    }

    @Test
    void registrarCompra_debeAgregarPuntosBase() {
        clienteService.agregarCliente("1", "Luis", "luis@mail.com");

        compraService.registrarCompra("c1", "1", 450, LocalDate.now());

        Cliente cliente = clienteService.obtenerCliente("1");
        assertEquals(4, cliente.getPuntos()); // 450 / 100 = 4
    }

    @Test
    void registrarCompra_conMultiplicadorPlata() {
        Cliente cliente = clienteService.agregarCliente("2", "Marta", "marta@mail.com");
        cliente.setPuntos(600);
        cliente.actualizarNivel();

        compraService.registrarCompra("c2", "2", 500, LocalDate.now());

        // Puntos base: 5, multiplicador Plata: 1.2 → 6 pts
        assertEquals(600 + 6, cliente.getPuntos());
        assertEquals("PLATA", cliente.getNivel().name());
    }

    @Test
    void registrarTerceraCompraDia_daBonus10Puntos() {
        clienteService.agregarCliente("3", "Sofia", "sofia@mail.com");
        LocalDate hoy = LocalDate.now();

        compraService.registrarCompra("c3a", "3", 300, hoy); // 3 pts
        compraService.registrarCompra("c3b", "3", 300, hoy); // 3 pts
        compraService.registrarCompra("c3c", "3", 300, hoy); // 3 pts + 10 bonus

        Cliente cliente = clienteService.obtenerCliente("3");
        assertEquals(3 + 3 + 13, cliente.getPuntos()); // 19
    }

    @Test
    void obtenerCompra_debeRetornarCompraCorrecta() {
        clienteService.agregarCliente("4", "Elena", "elena@mail.com");
        LocalDate fecha = LocalDate.now();
        compraService.registrarCompra("c4", "4", 500, fecha);

        Compra compra = compraService.obtenerCompra("c4");
        assertNotNull(compra);
        assertEquals("c4", compra.getIdCompra());
    }

    @Test
    void eliminarCompra_debeEliminarCompra() {
        clienteService.agregarCliente("5", "Pablo", "pablo@mail.com");
        LocalDate fecha = LocalDate.now();
        compraService.registrarCompra("c5", "5", 500, fecha);

        compraService.eliminarCompra("c5");
        assertNull(compraService.obtenerCompra("c5"));
    }
}
