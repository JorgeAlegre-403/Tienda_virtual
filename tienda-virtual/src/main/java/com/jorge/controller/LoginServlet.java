package com.jorge.controller;

import com.jorge.dao.UsuarioDAO;
import com.jorge.dao.factory.DAOFactory;
import com.jorge.dto.UsuarioDTO;
import com.jorge.util.PasswordUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
            UsuarioDTO usuario = usuarioDAO.buscarPorEmail(email);

            if (usuario != null &&
                    PasswordUtil.checkPassword(password, usuario.getPassword())) {

                request.getSession().setAttribute("usuario", usuario);
                response.sendRedirect("home");

            } else {
                request.setAttribute("error", "Credenciales incorrectas");
                request.getRequestDispatcher("WEB-INF/views/login.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {
            response.sendError(500);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/login.jsp")
                .forward(request, response);
    }
}