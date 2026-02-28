package com.jorge.dao.impl;

import com.jorge.dao.PedidoDAO;
import com.jorge.dto.PedidoDTO;
import com.jorge.model.Carrito;
import com.jorge.model.ItemCarrito;
import com.jorge.util.DBConnection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    private static final BigDecimal IVA_PORCENTAJE = new BigDecimal("0.21");

    @Override
    public void crearPedido(int idUsuario, Carrito carrito) throws Exception {

        Connection conn = null;
        PreparedStatement psPedido = null;
        PreparedStatement psLinea = null;
        ResultSet rs = null;

        try {

            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            BigDecimal importe = carrito.getTotal();
            BigDecimal iva = importe.multiply(IVA_PORCENTAJE)
                    .setScale(2, RoundingMode.HALF_UP);

            String sqlPedido = "INSERT INTO pedidos (Fecha, Estado, IdUsuario, Importe, Iva)"+
                        "VALUES (?, ?, ?, ?, ?)";

            psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);

            psPedido.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            psPedido.setString(2, "c"); // estado por defecto
            psPedido.setInt(3, idUsuario);
            psPedido.setBigDecimal(4, importe);
            psPedido.setBigDecimal(5, iva);

            psPedido.executeUpdate();

            rs = psPedido.getGeneratedKeys();

            if (!rs.next()) {
                throw new Exception("No se pudo obtener el IdPedido");
            }

            int idPedido = rs.getInt(1);

            String sqlLinea = "INSERT INTO lineaspedidos (IdPedido, IdProducto, Cantidad)" +
                        "VALUES (?, ?, ?)";

            psLinea = conn.prepareStatement(sqlLinea);

            for (ItemCarrito item : carrito.getItems()) {

                psLinea.setInt(1, idPedido);
                psLinea.setInt(2, item.getProducto().getIdProducto());
                psLinea.setInt(3, item.getCantidad());

                psLinea.addBatch();
            }

            psLinea.executeBatch();

            conn.commit();

        } catch (Exception e) {

            if (conn != null) {
                conn.rollback();
            }

            throw e;

        } finally {

            if (rs != null)
                rs.close();
            if (psLinea != null)
                psLinea.close();
            if (psPedido != null)
                psPedido.close();

            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public List<PedidoDTO> listarPorUsuario(int idUsuario) throws Exception {

        List<PedidoDTO> lista = new ArrayList<>();

        String sql = "SELECT IdPedido, Fecha, Estado, Importe, Iva"+
                    "FROM pedidos"+
                    "WHERE IdUsuario = ?"+
                    "ORDER BY Fecha DESC";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    PedidoDTO p = new PedidoDTO();

                    p.setIdPedido(rs.getInt("IdPedido"));
                    p.setFecha(rs.getDate("Fecha"));
                    p.setEstado(rs.getString("Estado"));
                    p.setImporte(rs.getBigDecimal("Importe"));
                    p.setIva(rs.getBigDecimal("Iva"));

                    lista.add(p);
                }
            }
        }

        return lista;
    }

    @Override
    public int crearPedido(PedidoDTO pedido) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'crearPedido'");
    }

    @Override
    public void actualizarPedido(PedidoDTO pedido) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'actualizarPedido'");
    }

    @Override
    public PedidoDTO buscarCarritoActivo(int idUsuario) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'buscarCarritoActivo'");
    }

    @Override
    public List<PedidoDTO> listarPedidosFinalizados(int idUsuario) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'listarPedidosFinalizados'");
    }

}