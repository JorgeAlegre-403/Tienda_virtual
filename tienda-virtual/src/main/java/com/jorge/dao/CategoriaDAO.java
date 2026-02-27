package com.jorge.dao;

import com.jorge.dto.CategoriaDTO;
import java.util.List;

public interface CategoriaDAO {
    List<CategoriaDTO> listarTodas() throws Exception;
}