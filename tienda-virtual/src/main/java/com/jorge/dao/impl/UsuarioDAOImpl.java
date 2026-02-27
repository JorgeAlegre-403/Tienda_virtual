package com.jorge.dao.impl;

import com.jorge.dao.UsuarioDAO;
import com.jorge.dto.UsuarioDTO;
import com.jorge.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void insertar(UsuarioDTO u) throws Exception {

        String sql = "INSERT INTO usuarios (Email,Password,Nombre,Apellidos,NIF," +
                "Telefono,Direccion,CodigoPostal,Localidad,Provincia,Avatar) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, u.getEmail());
                ps.setString(2, u.getPassword());
                ps.setString(3, u.getNombre());
                ps.setString(4, u.getApellidos());
                ps.setString(5, u.getNif());
                ps.setString(6, u.getTelefono());
                ps.setString(7, u.getDireccion());
                ps.setString(8, u.getCodigoPostal());
                ps.setString(9, u.getLocalidad());
                ps.setString(10, u.getProvincia());
                ps.setString(11, u.getAvatar());

                ps.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    @Override
    public UsuarioDTO buscarPorEmail(String email) throws Exception {

        String sql = "SELECT * FROM usuarios WHERE Email=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapUsuario(rs);
            }
        }
        return null;
    }

    @Override
    public UsuarioDTO buscarPorId(int id) throws Exception {

        String sql = "SELECT * FROM usuarios WHERE IdUsuario=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapUsuario(rs);
            }
        }
        return null;
    }

    @Override
    public void actualizar(UsuarioDTO u) throws Exception {

        String sql = "UPDATE usuarios SET Nombre=?,Apellidos=?,Telefono=?,Direccion=?," +
                "CodigoPostal=?,Localidad=?,Provincia=?,Avatar=? WHERE IdUsuario=?";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, u.getNombre());
                ps.setString(2, u.getApellidos());
                ps.setString(3, u.getTelefono());
                ps.setString(4, u.getDireccion());
                ps.setString(5, u.getCodigoPostal());
                ps.setString(6, u.getLocalidad());
                ps.setString(7, u.getProvincia());
                ps.setString(8, u.getAvatar());
                ps.setInt(9, u.getIdUsuario());

                ps.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    @Override
    public void actualizarPassword(int id, String nuevaPassword) throws Exception {

        String sql = "UPDATE usuarios SET Password=? WHERE IdUsuario=?";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, nuevaPassword);
                ps.setInt(2, id);

                ps.executeUpdate();
                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<UsuarioDTO> listarTodos() throws Exception {

        List<UsuarioDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapUsuario(rs));
            }
        }
        return lista;
    }

    private UsuarioDTO mapUsuario(ResultSet rs) throws SQLException {

        UsuarioDTO u = new UsuarioDTO();

        u.setIdUsuario(rs.getInt("IdUsuario"));
        u.setEmail(rs.getString("Email"));
        u.setPassword(rs.getString("Password"));
        u.setNombre(rs.getString("Nombre"));
        u.setApellidos(rs.getString("Apellidos"));
        u.setNif(rs.getString("NIF"));
        u.setTelefono(rs.getString("Telefono"));
        u.setDireccion(rs.getString("Direccion"));
        u.setCodigoPostal(rs.getString("CodigoPostal"));
        u.setLocalidad(rs.getString("Localidad"));
        u.setProvincia(rs.getString("Provincia"));
        u.setAvatar(rs.getString("Avatar"));

        return u;
    }
}