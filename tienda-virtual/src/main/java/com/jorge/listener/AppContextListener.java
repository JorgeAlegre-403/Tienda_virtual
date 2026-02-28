package com.jorge.listener;

import com.jorge.dao.CategoriaDAO;
import com.jorge.dao.factory.DAOFactory;
import com.jorge.dto.CategoriaDTO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            CategoriaDAO categoriaDAO = DAOFactory.getCategoriaDAO();
            List<CategoriaDTO> categorias = categoriaDAO.listarTodas();

            ServletContext context = sce.getServletContext();
            context.setAttribute("categorias", categorias);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}