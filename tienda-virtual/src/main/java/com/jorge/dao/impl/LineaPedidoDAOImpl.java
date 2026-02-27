package com.jorge.dao.impl;

import com.jorge.dao.LineaPedidoDAO;
import com.jorge.dto.LineaPedidoDTO;
import com.jorge.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineaPedidoDAOImpl implements LineaPedidoDAO {

    @Override
    public void insertarLinea(LineaPedidoDTO linea) throws Exception {

        String sql = "INSERT INTO lineaspedidos (IdPedido,IdProducto,Cantidad) VALUES (?,?,?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, linea.getIdPedido());
                ps.setInt(2, linea.getIdProducto());
                ps.setInt(3, linea.getCantidad());

                ps.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    @Override
    public void actualizarCantidad(int idLinea, int cantidad) throws Exception {

        String sql = "UPDATE lineaspedidos SET Cantidad=? WHERE IdLinea=?";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, cantidad);
                ps.setInt(2, idLinea);

                ps.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    @Override
    public void eliminarLinea(int idLinea) throws Exception {

        String sql = "DELETE FROM lineaspedidos WHERE IdLinea=?";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, idLinea);
                ps.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<LineaPedidoDTO> listarPorPedido(int idPedido) throws Exception {

        List<LineaPedidoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM lineaspedidos WHERE IdPedido=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LineaPedidoDTO l = new LineaPedidoDTO();
                l.setIdLinea(rs.getInt("IdLinea"));
                l.setIdPedido(rs.getInt("IdPedido"));
                l.setIdProducto(rs.getInt("IdProducto"));
                l.setCantidad(rs.getInt("Cantidad"));

                lista.add(l);
            }
        }
        return lista;
    }
}