package com.jorge.dao;

import com.jorge.dto.UsuarioDTO;
import java.util.List;

public interface UsuarioDAO {

    void insertar(UsuarioDTO usuario) throws Exception;

    UsuarioDTO buscarPorEmail(String email) throws Exception;

    UsuarioDTO buscarPorId(int id) throws Exception;

    void actualizar(UsuarioDTO usuario) throws Exception;

    void actualizarPassword(int id, String nuevaPassword) throws Exception;

    List<UsuarioDTO> listarTodos() throws Exception;
}