package com.jorge.dao;

import com.jorge.dto.ProductoDTO;
import java.util.List;

public interface ProductoDAO {

    List<ProductoDTO> listarTodos() throws Exception;

    ProductoDTO buscarPorId(int id) throws Exception;

    List<ProductoDTO> buscarPorCategoria(int idCategoria) throws Exception;

    List<ProductoDTO> buscarPorNombre(String nombre) throws Exception;
}