package com.jorge.dto;

public class LineaPedidoDTO {

    private int idLinea;
    private int idPedido;
    private int idProducto;
    private int cantidad;

    public LineaPedidoDTO() {}

    public LineaPedidoDTO(int idLinea, int idPedido,
                          int idProducto, int cantidad) {
        this.idLinea = idLinea;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}