package com.proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        GestionOrdenes gestionOrdenes = new GestionOrdenes();
        GestionFacturas gestionFacturas = new GestionFacturas();
        List<Cliente> clientes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== Sistema de Gestión de Inventario y Facturación ===");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Inventario");
            System.out.println("3. Registrar Cliente");
            System.out.println("4. Crear Orden de Compra");
            System.out.println("5. Modificar Orden de Compra");
            System.out.println("6. Mostrar Órdenes de Compra");
            System.out.println("7. Generar Factura");
            System.out.println("8. Mostrar Facturas");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarProducto(inventario, scanner);
                    break;

                case 2:
                    inventario.mostrarInventario();
                    break;

                case 3:
                    registrarCliente(clientes, scanner);
                    break;

                case 4:
                    crearOrdenCompra(gestionOrdenes, inventario, clientes, scanner);
                    break;

                case 5:
                    modificarOrdenCompra(gestionOrdenes, inventario, scanner);
                    break;

                case 6:
                    gestionOrdenes.mostrarOrdenesCompra();
                    break;

                case 7:
                    generarFactura(gestionOrdenes, gestionFacturas, scanner);
                    break;

                case 8:
                    gestionFacturas.mostrarFacturas();
                    break;

                case 9:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 9);

        scanner.close();
    }

    private static void agregarProducto(Inventario inventario, Scanner scanner) {
        System.out.print("Ingrese código del producto: ");
        String codigo = scanner.next();
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.next();
        System.out.print("Ingrese tipo (Local/Importado): ");
        String tipo = scanner.next();
        System.out.print("Ingrese cantidad: ");
        int cantidad = scanner.nextInt();
        System.out.print("Ingrese precio: ");
        double precio = scanner.nextDouble();

        Producto producto = new Producto(codigo, nombre, tipo, cantidad, precio);
        inventario.agregarProducto(producto);
        System.out.println("Producto agregado con éxito.");
    }

    private static void registrarCliente(List<Cliente> clientes, Scanner scanner) {
        System.out.print("Ingrese ID del cliente: ");
        String idCliente = scanner.next();
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.next();
        System.out.print("Ingrese dirección del cliente: ");
        String direccion = scanner.next();
        System.out.print("Ingrese teléfono del cliente: ");
        String telefono = scanner.next();

        Cliente cliente = new Cliente(idCliente, nombre, direccion, telefono);
        clientes.add(cliente);
        System.out.println("Cliente registrado con éxito.");
    }

    private static void crearOrdenCompra(GestionOrdenes gestionOrdenes, Inventario inventario, List<Cliente> clientes, Scanner scanner) {
        System.out.print("Ingrese el número de orden de compra: ");
        String numeroOrden = scanner.next();

        // Seleccionar un cliente para la orden de compra
        System.out.println("Seleccione un cliente por ID:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
        String idCliente = scanner.next();
        Cliente clienteSeleccionado = null;
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(idCliente)) {
                clienteSeleccionado = cliente;
                break;
            }
        }

        if (clienteSeleccionado == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        List<Producto> productosOrden = new ArrayList<>();
        String codigo;
        do {
            System.out.print("Ingrese el código del producto (o '0' para finalizar): ");
            codigo = scanner.next();
            if (!codigo.equals("0")) {
                Producto producto = inventario.buscarProducto(codigo);
                if (producto != null) {
                    productosOrden.add(producto);
                    System.out.println("Producto agregado a la orden.");
                } else {
                    System.out.println("Producto no encontrado en inventario.");
                }
            }
        } while (!codigo.equals("0"));

        OrdenCompra ordenCompra = new OrdenCompra(numeroOrden, clienteSeleccionado, productosOrden);
        gestionOrdenes.crearOrdenCompra(ordenCompra);
    }

    private static void modificarOrdenCompra(GestionOrdenes gestionOrdenes, Inventario inventario, Scanner scanner) {
        System.out.print("Ingrese el número de la orden de compra a modificar: ");
        String numeroOrden = scanner.next();
        OrdenCompra orden = gestionOrdenes.buscarOrdenCompra(numeroOrden);
        if (orden != null) {
            List<Producto> nuevosProductos = new ArrayList<>();
            String codigo;
            do {
                System.out.print("Ingrese el código del nuevo producto (o '0' para finalizar): ");
                codigo = scanner.next();
                if (!codigo.equals("0")) {
                    Producto producto = inventario.buscarProducto(codigo);
                    if (producto != null) {
                        nuevosProductos.add(producto);
                        System.out.println("Producto agregado a la nueva orden.");
                    } else {
                        System.out.println("Producto no encontrado en inventario.");
                    }
                }
            } while (!codigo.equals("0"));

            gestionOrdenes.modificarOrdenCompra(numeroOrden, nuevosProductos);
        } else {
            System.out.println("Orden de compra no encontrada.");
        }
    }

    private static void generarFactura(GestionOrdenes gestionOrdenes, GestionFacturas gestionFacturas, Scanner scanner) {
        System.out.print("Ingrese el número de la orden de compra para generar la factura: ");
        String numeroOrden = scanner.next();
        OrdenCompra ordenCompra = gestionOrdenes.buscarOrdenCompra(numeroOrden);
        if (ordenCompra != null && ordenCompra.getEstado().equals("Pendiente")) {
            gestionFacturas.crearFactura(ordenCompra);
        } else {
            System.out.println("Orden de compra no válida o ya facturada.");
        }
    }
}
