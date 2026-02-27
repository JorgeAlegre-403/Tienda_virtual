package com.jorge.dao.impl;

import com.jorge.dao.PedidoDAO;
import com.jorge.dto.PedidoDTO;
import com.jorge.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    @Override
    public int crearPedido(PedidoDTO pedido) throws Exception {

        String sql = "INSERT INTO pedidos (Fecha,Estado,IdUsuario,Importe,Iva) VALUES (?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setDate(1, Date.valueOf(pedido.getFecha()));
                ps.setString(2, pedido.getEstado());
                ps.setInt(3, pedido.getIdUsuario());
                ps.setDouble(4, pedido.getImporte());
                ps.setDouble(5, pedido.getIva());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                int idGenerado = 0;

                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }

                con.commit();
                return idGenerado;

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    @Override
    public void actualizarPedido(PedidoDTO pedido) throws Exception {

        String sql = "UPDATE pedidos SET Estado=?,Importe=?,Iva=? WHERE IdPedido=?";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, pedido.getEstado());
                ps.setDouble(2, pedido.getImporte());
                ps.setDouble(3, pedido.getIva());
                ps.setInt(4, pedido.getIdPedido());

                ps.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    @Override
    public PedidoDTO buscarCarritoActivo(int idUsuario) throws Exception {

        String sql = "SELECT * FROM pedidos WHERE IdUsuario=? AND Estado='c'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapPedido(rs);
            }
        }
        return null;
    }

    @Override
    public List<PedidoDTO> listarPedidosFinalizados(int idUsuario) throws Exception {

        List<PedidoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE IdUsuario=? AND Estado='f'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapPedido(rs));
            }
        }
        return lista;
    }

    private PedidoDTO mapPedido(ResultSet rs) throws SQLException {

        PedidoDTO p = new PedidoDTO();
        p.setIdPedido(rs.getInt("IdPedido"));
        p.setFecha(rs.getDate("Fecha").toLocalDate());
        p.setEstado(rs.getString("Estado"));
        p.setIdUsuario(rs.getInt("IdUsuario"));
        p.setImporte(rs.getDouble("Importe"));
        p.setIva(rs.getDouble("Iva"));

        return p;
    }
}