package com.jorge.controller;

import com.jorge.dao.PedidoDAO;
import com.jorge.dao.factory.DAOFactory;
import com.jorge.dto.UsuarioDTO;
import com.jorge.model.Carrito;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class PedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/confirmar.jsp")
                .forward(request, response);
        if ("true".equals(request.getParameter("exito"))) {
            request.setAttribute("mensaje", "Pedido realizado correctamente");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (usuario == null || carrito == null || carrito.estaVacio()) {
            response.sendRedirect("home");
            return;
        }

        try {

            PedidoDAO pedidoDAO = DAOFactory.getPedidoDAO();
            pedidoDAO.crearPedido(usuario.getId(), carrito);

            session.removeAttribute("carrito");

            response.sendRedirect("pedido?exito=true");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}