package com.jorge.dao.impl;

import com.jorge.dao.CategoriaDAO;
import com.jorge.dto.CategoriaDTO;
import com.jorge.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public List<CategoriaDTO> listarTodas() throws Exception {

        List<CategoriaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoriaDTO c = new CategoriaDTO();
                c.setIdCategoria(rs.getInt("IdCategoria"));
                c.setNombre(rs.getString("Nombre"));
                c.setImagen(rs.getString("Imagen"));
                lista.add(c);
            }
        }
        return lista;
    }
}