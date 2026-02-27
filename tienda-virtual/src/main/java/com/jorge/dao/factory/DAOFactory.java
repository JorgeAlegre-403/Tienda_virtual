package com.jorge.dao.factory;

import com.jorge.dao.*;
import com.jorge.dao.impl.*;

public class DAOFactory {

    public static UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOImpl();
    }

    public static ProductoDAO getProductoDAO() {
        return new ProductoDAOImpl();
    }

    public static CategoriaDAO getCategoriaDAO() {
        return new CategoriaDAOImpl();
    }

    public static PedidoDAO getPedidoDAO() {
        return new PedidoDAOImpl();
    }

    public static LineaPedidoDAO getLineaPedidoDAO() {
        return new LineaPedidoDAOImpl();
    }
}