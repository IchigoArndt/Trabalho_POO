package com.trabalho.projetofaculdade.service;

import com.trabalho.projetofaculdade.dto.ExemploRequestDTO;
import com.trabalho.projetofaculdade.dto.ExemploResponseDTO;

import java.util.List;

public interface ExemploService {

    List<ExemploResponseDTO> listarTodos();

    ExemploResponseDTO buscarPorId(Long id);

    ExemploResponseDTO criar(ExemploRequestDTO dto);

    ExemploResponseDTO atualizar(Long id, ExemploRequestDTO dto);

    void deletar(Long id);
}
