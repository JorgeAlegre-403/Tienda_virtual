package com.jorge.dao.impl;

import com.jorge.dao.ProductoDAO;
import com.jorge.dto.ProductoDTO;
import com.jorge.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public List<ProductoDTO> listarTodos() throws Exception {

        List<ProductoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapProducto(rs));
            }
        }
        return lista;
    }

    @Override
    public ProductoDTO buscarPorId(int id) throws Exception {

        String sql = "SELECT * FROM productos WHERE IdProducto=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return mapProducto(rs);
        }
        return null;
    }

    @Override
    public List<ProductoDTO> buscarPorCategoria(int idCategoria) throws Exception {

        List<ProductoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE IdCategoria=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapProducto(rs));
            }
        }
        return lista;
    }

    @Override
    public List<ProductoDTO> buscarPorNombre(String nombre) throws Exception {

        List<ProductoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE Nombre LIKE ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapProducto(rs));
            }
        }
        return lista;
    }

    private ProductoDTO mapProducto(ResultSet rs) throws SQLException {

        ProductoDTO p = new ProductoDTO();
        p.setIdProducto(rs.getInt("IdProducto"));
        p.setIdCategoria(rs.getInt("IdCategoria"));
        p.setNombre(rs.getString("Nombre"));
        p.setDescripcion(rs.getString("Descripcion"));
        p.setPrecio(rs.getDouble("Precio"));
        p.setMarca(rs.getString("Marca"));
        p.setImagen(rs.getString("Imagen"));

        return p;
    }
}