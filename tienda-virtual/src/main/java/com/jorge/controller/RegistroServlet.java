package com.jorge.controller;

import com.jorge.dao.UsuarioDAO;
import com.jorge.dao.factory.DAOFactory;
import com.jorge.dto.UsuarioDTO;
import com.jorge.util.PasswordUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            UsuarioDTO u = new UsuarioDTO();
            u.setEmail(request.getParameter("email"));
            u.setPassword(PasswordUtil.hashPassword(request.getParameter("password")));
            u.setNombre(request.getParameter("nombre"));
            u.setApellidos(request.getParameter("apellidos"));
            u.setNif(request.getParameter("nif"));
            u.setTelefono(request.getParameter("telefono"));
            u.setDireccion(request.getParameter("direccion"));
            u.setCodigoPostal(request.getParameter("codigoPostal"));
            u.setLocalidad(request.getParameter("localidad"));
            u.setProvincia(request.getParameter("provincia"));
            u.setAvatar("default.jpg");

            UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
            usuarioDAO.insertar(u);

            response.sendRedirect("login");

        } catch (Exception e) {
            response.sendError(500);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/registro.jsp")
                .forward(request, response);
    }
}