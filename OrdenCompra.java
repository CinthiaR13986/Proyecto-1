package com.proyecto;

import java.util.List;

public class OrdenCompra {
    private String numeroOrden;
    private List<Producto> productos;
    private String estado; // Pendiente, Completada, Cancelada

    public OrdenCompra(String numeroOrden, Cliente clienteSeleccionado, List<Producto> productos) {
        this.numeroOrden = numeroOrden;
        this.productos = productos;
        this.estado = "Pendiente";
    }

    // Getters y Setters
    public String getNumeroOrden() {
        return numeroOrden;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
