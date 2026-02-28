package com.jorge.controller;

import com.jorge.dao.PedidoDAO;
import com.jorge.dao.factory.DAOFactory;
import com.jorge.dto.UsuarioDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MisPedidosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");

            if (usuario == null) {
                response.sendRedirect("login");
                return;
            }

            PedidoDAO pedidoDAO = DAOFactory.getPedidoDAO();

            request.setAttribute("pedidos",
                    pedidoDAO.listarPorUsuario(usuario.getIdUsuario()));

            request.getRequestDispatcher("WEB-INF/views/misPedidos.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            response.sendError(500);
        }
    }
}