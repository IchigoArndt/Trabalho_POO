package com.trabalho.projetofaculdade.controller.book;

import com.trabalho.projetofaculdade.dto.book.BookRequestDTO;
import com.trabalho.projetofaculdade.dto.book.BookResponseDTO;
import com.trabalho.projetofaculdade.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> listarTodos() {
        return ResponseEntity.ok(bookService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> criar(@RequestBody @Valid BookRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid BookRequestDTO dto) {
        return ResponseEntity.ok(bookService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        bookService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
