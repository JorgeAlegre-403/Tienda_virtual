package com.jorge.model;

import com.jorge.dto.ProductoDTO;

import java.math.BigDecimal;

public class ItemCarrito {

    private ProductoDTO producto;
    private int cantidad;

    public ItemCarrito(ProductoDTO producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void incrementar() {
        this.cantidad++;
    }

    public void disminuir() {
        if (cantidad > 1) {
            this.cantidad--;
        }
    }

    public BigDecimal getSubtotal() {
        return producto.getPrecio().multiply(new BigDecimal(cantidad));
    }
}