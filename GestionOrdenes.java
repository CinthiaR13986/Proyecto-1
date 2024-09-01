package com.proyecto;

import java.util.ArrayList;
import java.util.List;

public class GestionOrdenes {
    private List<OrdenCompra> ordenes;

    public GestionOrdenes() {
        this.ordenes = new ArrayList<>();
    }

    public void crearOrdenCompra(OrdenCompra orden) {
        ordenes.add(orden);
        System.out.println("Orden de compra creada con éxito.");
    }

    public OrdenCompra buscarOrdenCompra(String numeroOrden) {
        for (OrdenCompra orden : ordenes) {
            if (orden.getNumeroOrden().equals(numeroOrden)) {
                return orden;
            }
        }
        return null;
    }

    public void modificarOrdenCompra(String numeroOrden, List<Producto> nuevosProductos) {
        OrdenCompra orden = buscarOrdenCompra(numeroOrden);
        if (orden != null) {
            orden.setEstado("Modificada");
            orden.getProductos().clear();
            orden.getProductos().addAll(nuevosProductos);
            System.out.println("Orden de compra modificada con éxito.");
        } else {
            System.out.println("Orden de compra no encontrada.");
        }
    }

    public void mostrarOrdenesCompra() {
        for (OrdenCompra orden : ordenes) {
            System.out.println("Número de Orden: " + orden.getNumeroOrden());
            System.out.println("Estado: " + orden.getEstado());
            System.out.println("Productos: ");
            for (Producto p : orden.getProductos()) {
                System.out.println(p);
            }
        }
    }
}
