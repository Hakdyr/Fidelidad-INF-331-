package com.empresa.fidelidad;

import com.empresa.fidelidad.model.Cliente;
import com.empresa.fidelidad.service.ClienteService;
import com.empresa.fidelidad.service.CompraService;

import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();
        CompraService compraService = new CompraService(clienteService);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== Programa de Fidelidad =====");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Registrar Compra");
            System.out.println("3. Mostrar Puntos y Nivel de Cliente");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> gestionClientes(scanner, clienteService);
                case 2 -> registrarCompra(scanner, compraService);
                case 3 -> mostrarPuntos(scanner, clienteService);
                case 4 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void gestionClientes(Scanner scanner, ClienteService service) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Volver");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();
                    try {
                        service.agregarCliente(id, nombre, correo);
                        System.out.println("Cliente agregado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\nClientes:");
                    service.listarClientes().forEach(c -> 
                        System.out.println(c.getId() + " - " + c.getNombre() + " - " + c.getCorreo() + 
                                           " - Puntos: " + c.getPuntos() + " - Nivel: " + c.getNivel())
                    );
                }
                case 3 -> {
                    System.out.print("ID del cliente a actualizar: ");
                    String id = scanner.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Nuevo correo: ");
                    String correo = scanner.nextLine();
                    service.actualizarCliente(id, nombre, correo);
                    System.out.println("Cliente actualizado.");
                }
                case 4 -> {
                    System.out.print("ID del cliente a eliminar: ");
                    String id = scanner.nextLine();
                    service.eliminarCliente(id);
                    System.out.println("Cliente eliminado.");
                }
                case 5 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5);
    }

    private static void registrarCompra(Scanner scanner, CompraService service) {
        System.out.print("ID Compra: ");
        String idCompra = scanner.nextLine();
        System.out.print("ID Cliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("Monto: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        try {
            service.registrarCompra(idCompra, idCliente, monto, LocalDate.now());
            System.out.println("Compra registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void mostrarPuntos(Scanner scanner, ClienteService service) {
        System.out.print("ID Cliente: ");
        String idCliente = scanner.nextLine();
        Cliente cliente = service.obtenerCliente(idCliente);
        if (cliente != null) {
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Puntos: " + cliente.getPuntos());
            System.out.println("Nivel: " + cliente.getNivel());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
}
