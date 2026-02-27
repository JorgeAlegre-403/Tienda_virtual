package com.jorge.dao;

import com.jorge.dto.LineaPedidoDTO;
import java.util.List;

public interface LineaPedidoDAO {

    void insertarLinea(LineaPedidoDTO linea) throws Exception;

    void actualizarCantidad(int idLinea, int cantidad) throws Exception;

    void eliminarLinea(int idLinea) throws Exception;

    List<LineaPedidoDTO> listarPorPedido(int idPedido) throws Exception;
}