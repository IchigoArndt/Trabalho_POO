package com.trabalho.projetofaculdade.service.Book;

import com.trabalho.projetofaculdade.dto.book.BookRequestDTO;
import com.trabalho.projetofaculdade.dto.book.BookResponseDTO;

import java.util.List;

public interface BookService {

    List<BookResponseDTO> GetAll();

    BookResponseDTO GetById(Long id);

    BookResponseDTO Add(BookRequestDTO dto);

    BookResponseDTO Update(Long id, BookRequestDTO dto);

    void Delete(Long id);

}
