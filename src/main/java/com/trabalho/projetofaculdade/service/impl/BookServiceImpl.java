package com.trabalho.projetofaculdade.service.impl;

import com.trabalho.projetofaculdade.dto.book.BookRequestDTO;
import com.trabalho.projetofaculdade.dto.book.BookResponseDTO;
import com.trabalho.projetofaculdade.exception.RecursoNaoEncontradoException;
import com.trabalho.projetofaculdade.model.Book;
import com.trabalho.projetofaculdade.repository.BookRepository;
import com.trabalho.projetofaculdade.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookResponseDTO> listarTodos() {
        return bookRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO buscarPorId(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado com id: " + id));
        return toResponseDTO(book);
    }

    @Override
    public BookResponseDTO criar(BookRequestDTO dto) {
        Book book = Book.builder()
                .Title(dto.getTitle())
                .QuantityPages(dto.getQuantityPages())
                .Author(dto.getAuthor())
                .ISBN(dto.getISBN())
                .Publisher(dto.getPublisher())
                .YearPublish(dto.getYearPublish())
                .Category(dto.getCategory())
                .build();
        return toResponseDTO(bookRepository.save(book));
    }

    @Override
    public BookResponseDTO atualizar(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado com id: " + id));

        book.setTitle(dto.getTitle());
        book.setQuantityPages(dto.getQuantityPages());
        book.setAuthor(dto.getAuthor());
        book.setISBN(dto.getISBN());
        book.setPublisher(dto.getPublisher());
        book.setYearPublish(dto.getYearPublish());
        book.setCategory(dto.getCategory());

        return toResponseDTO(bookRepository.save(book));
    }

    @Override
    public void deletar(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Livro não encontrado com id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private BookResponseDTO toResponseDTO(Book book) {
        return BookResponseDTO.builder()
                .Id(book.getId())
                .Title(book.getTitle())
                .QuantityPages(book.getQuantityPages())
                .Author(book.getAuthor())
                .build();
    }
}