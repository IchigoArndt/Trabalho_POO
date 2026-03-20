package com.trabalho.projetofaculdade.controller;

import com.trabalho.projetofaculdade.dto.ExemploRequestDTO;
import com.trabalho.projetofaculdade.dto.ExemploResponseDTO;
import com.trabalho.projetofaculdade.service.ExemploService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exemplos")
@RequiredArgsConstructor
public class ExemploController {

    private final ExemploService exemploService;

    @GetMapping
    public ResponseEntity<List<ExemploResponseDTO>> listarTodos() {
        return ResponseEntity.ok(exemploService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExemploResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(exemploService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ExemploResponseDTO> criar(@RequestBody @Valid ExemploRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exemploService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExemploResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ExemploRequestDTO dto) {
        return ResponseEntity.ok(exemploService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        exemploService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
