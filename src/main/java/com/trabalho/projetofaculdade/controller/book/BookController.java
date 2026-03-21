package com.trabalho.projetofaculdade.controller.book;

import com.trabalho.projetofaculdade.dto.book.BookRequestDTO;
import com.trabalho.projetofaculdade.dto.book.BookResponseDTO;
import com.trabalho.projetofaculdade.service.Book.BookService;
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
    public ResponseEntity<List<BookResponseDTO>> GetAll() {
        return ResponseEntity.ok(bookService.GetAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> GetById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.GetById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> Add(@RequestBody @Valid BookRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.Add(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> Update(
            @PathVariable Long id,
            @RequestBody @Valid BookRequestDTO dto) {
        return ResponseEntity.ok(bookService.Update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) {
        bookService.Delete(id);
        return ResponseEntity.noContent().build();
    }

}
