package com.mvdesafio.backend.service;

import com.mvdesafio.backend.dto.CafeDaManhaDTO;
import java.util.List;

public interface CafeDaManhaService {
    CafeDaManhaDTO cadastrar(CafeDaManhaDTO dto);
    List<CafeDaManhaDTO> listarTodos();
    CafeDaManhaDTO editar(Long id, CafeDaManhaDTO dto);
}
