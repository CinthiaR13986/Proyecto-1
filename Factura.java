package com.proyecto;

public class Factura {
    private String numeroFactura;
    private OrdenCompra ordenCompra;
    private double total;

    public Factura(String numeroFactura, OrdenCompra ordenCompra) {
        this.numeroFactura = numeroFactura;
        this.ordenCompra = ordenCompra;
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        double sum = 0;
        for (Producto p : ordenCompra.getProductos()) {
            sum += p.getCantidad() * p.getPrecio();
        }
        return sum;
    }

    // Getters y Setters
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public double getTotal() {
        return total;
    }
}
