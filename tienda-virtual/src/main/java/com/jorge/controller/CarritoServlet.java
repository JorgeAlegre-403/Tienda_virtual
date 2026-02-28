package com.jorge.controller;

import com.jorge.dao.ProductoDAO;
import com.jorge.dao.factory.DAOFactory;
import com.jorge.dto.ProductoDTO;
import com.jorge.model.Carrito;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/carrito.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String accion = request.getParameter("accion");
            int idProducto = Integer.parseInt(request.getParameter("id"));

            HttpSession session = request.getSession();
            Carrito carrito = (Carrito) session.getAttribute("carrito");

            if (carrito == null) {
                carrito = new Carrito();
                session.setAttribute("carrito", carrito);
            }

            ProductoDAO productoDAO = DAOFactory.getProductoDAO();
            ProductoDTO producto = productoDAO.buscarPorId(idProducto);

            switch (accion) {

                case "agregar":
                    carrito.agregarProducto(producto);
                    break;

                case "eliminar":
                    carrito.eliminarProducto(idProducto);
                    break;

                case "disminuir":
                    carrito.disminuirProducto(idProducto);
                    break;
            }

            response.sendRedirect("carrito");

        } catch (Exception e) {
            response.sendError(500);
        }
    }
}