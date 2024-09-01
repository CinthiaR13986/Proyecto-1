package com.proyecto;

import java.util.ArrayList;
import java.util.List;

public class GestionFacturas {
    private List<Factura> facturas;

    public GestionFacturas() {
        this.facturas = new ArrayList<>();
    }

    public void crearFactura(OrdenCompra ordenCompra) {
        if (ordenCompra != null && ordenCompra.getEstado().equals("Pendiente")) {
            String numeroFactura = "FAC" + (facturas.size() + 1);
            Factura factura = new Factura(numeroFactura, ordenCompra);
            facturas.add(factura);
            ordenCompra.setEstado("Facturada");
            System.out.println("Factura generada con éxito. Número de factura: " + numeroFactura);
        } else {
            System.out.println("No se puede generar la factura. Orden de compra no válida o ya facturada.");
        }
    }

    public void mostrarFacturas() {
        for (Factura factura : facturas) {
            System.out.println("Número de Factura: " + factura.getNumeroFactura());
            System.out.println("Total: " + factura.getTotal());
            System.out.println("Orden de Compra Asociada: " + factura.getOrdenCompra().getNumeroOrden());
        }
    }
}
