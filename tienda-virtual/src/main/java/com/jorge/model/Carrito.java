package com.jorge.model;

import com.jorge.dto.ProductoDTO;

import java.math.BigDecimal;
import java.util.*;

public class Carrito {

    private Map<Integer, ItemCarrito> items = new LinkedHashMap<>();

    public void agregarProducto(ProductoDTO producto) {

        if (items.containsKey(producto.getId())) {
            items.get(producto.getId()).incrementar();
        } else {
            items.put(producto.getId(), new ItemCarrito(producto, 1));
        }
    }

    public void eliminarProducto(int idProducto) {
        items.remove(idProducto);
    }

    public void disminuirProducto(int idProducto) {
        if (items.containsKey(idProducto)) {
            items.get(idProducto).disminuir();
        }
    }

    public Collection<ItemCarrito> getItems() {
        return items.values();
    }

    public BigDecimal getTotal() {
        return items.values()
                .stream()
                .map(ItemCarrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }
}