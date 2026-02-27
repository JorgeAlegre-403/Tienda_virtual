package com.jorge.dao;

import com.jorge.dto.PedidoDTO;
import java.util.List;

public interface PedidoDAO {

    int crearPedido(PedidoDTO pedido) throws Exception;

    void actualizarPedido(PedidoDTO pedido) throws Exception;

    PedidoDTO buscarCarritoActivo(int idUsuario) throws Exception;

    List<PedidoDTO> listarPedidosFinalizados(int idUsuario) throws Exception;
}