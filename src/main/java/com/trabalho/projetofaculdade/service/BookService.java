package com.trabalho.projetofaculdade.service;

import com.trabalho.projetofaculdade.dto.book.BookRequestDTO;
import com.trabalho.projetofaculdade.dto.book.BookResponseDTO;

import java.util.List;

public interface BookService {

    List<BookResponseDTO> listarTodos();

    BookResponseDTO buscarPorId(Long id);

    BookResponseDTO criar(BookRequestDTO dto);

    BookResponseDTO atualizar(Long id, BookRequestDTO dto);

    void deletar(Long id);

}
