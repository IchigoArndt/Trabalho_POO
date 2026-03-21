package com.trabalho.projetofaculdade.service.impl.Book;

import com.trabalho.projetofaculdade.dto.book.BookRequestDTO;
import com.trabalho.projetofaculdade.dto.book.BookResponseDTO;
import com.trabalho.projetofaculdade.exception.RecursoNaoEncontradoException;
import com.trabalho.projetofaculdade.model.Book;
import com.trabalho.projetofaculdade.repository.Book.BookRepository;
import com.trabalho.projetofaculdade.service.Book.BookService;
import com.trabalho.projetofaculdade.service.Log.ActionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ActionLogService actionLogService;

    @Override
    public List<BookResponseDTO> GetAll() {

        try{
            return bookRepository.findAll()
                    .stream()
                    .map(this::toResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            actionLogService.log("Book", "ERROR", "Ocorreu um erro inesperado ao buscar todos os registros.\n Stack Trace: " + e.getStackTrace() + "Mensagem:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookResponseDTO GetById(Long id) {

        try{
            Book book = bookRepository.findById(id)
            .orElseThrow(() -> {
                actionLogService.log("Book", "ERROR", "Livro não encontrado com id: " + id);
                return new RecursoNaoEncontradoException("Livro não encontrado com id: " + id);
            });
            return toResponseDTO(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookResponseDTO Add(BookRequestDTO dto) {

        try{
            Book book = Book.builder()
                    .Title(dto.getTitle())
                    .QuantityPages(dto.getQuantityPages())
                    .Author(dto.getAuthor())
                    .ISBN(dto.getISBN())
                    .Publisher(dto.getPublisher())
                    .YearPublish(dto.getYearPublish())
                    .Category(dto.getCategory())
                    .Available(true)
                    .build();

            actionLogService.log("Book", "ADD", "Livro criado com titulo: " + dto.getTitle());

            return toResponseDTO(bookRepository.save(book));
        } catch (Exception e) {
            actionLogService.log("Book", "ERROR", "Ocorreu um erro inesperado ao adicionar um novo livro.\n Stack Trace: " + e.getStackTrace() + "Mensagem:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookResponseDTO Update(Long id, BookRequestDTO dto) {

        try{
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> {
                        actionLogService.log("Book", "ERROR", "Livro não encontrado com id: " + id);
                        return new RecursoNaoEncontradoException("Livro não encontrado com id: " + id);
                    });

            book.setTitle(dto.getTitle());
            book.setQuantityPages(dto.getQuantityPages());
            book.setAuthor(dto.getAuthor());
            book.setISBN(dto.getISBN());
            book.setPublisher(dto.getPublisher());
            book.setYearPublish(dto.getYearPublish());
            book.setCategory(dto.getCategory());

            actionLogService.log("Book", "UPDATE", "Livro atualizado com id: " + id);
            return toResponseDTO(bookRepository.save(book));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void Delete(Long id) {

        try{
            if (!bookRepository.existsById(id)) {
                actionLogService.log("Book", "ERROR", "Livro não encontrado com id: " + id);
                throw new RecursoNaoEncontradoException("Livro não encontrado com id: " + id);
            }
            bookRepository.deleteById(id);
            actionLogService.log("Book", "DELETE", "Livro apagado com sucesso");
        } catch (Exception e) {
            actionLogService.log("Book", "ERROR", "Ocorreu um erro inesperado ao apagar um livro.\n Stack Trace: " + e.getStackTrace() + "Mensagem:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private BookResponseDTO toResponseDTO(Book book) {
        return BookResponseDTO.builder()
                .Id(book.getId())
                .Title(book.getTitle())
                .QuantityPages(book.getQuantityPages())
                .Author(book.getAuthor())
                .Available(book.getAvailable())
                .build();
    }
}