package com.jorge.controller;

import com.jorge.dao.ProductoDAO;
import com.jorge.dao.factory.DAOFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProductoDAO productoDAO = DAOFactory.getProductoDAO();
            request.setAttribute("productos", productoDAO.listarTodos());

            request.getRequestDispatcher("WEB-INF/views/home.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            response.sendError(500);
        }
    }
}