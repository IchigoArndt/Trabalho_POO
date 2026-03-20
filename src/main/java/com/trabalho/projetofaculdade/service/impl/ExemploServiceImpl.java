package com.trabalho.projetofaculdade.service.impl;

import com.trabalho.projetofaculdade.dto.ExemploRequestDTO;
import com.trabalho.projetofaculdade.dto.ExemploResponseDTO;
import com.trabalho.projetofaculdade.exception.RecursoNaoEncontradoException;
import com.trabalho.projetofaculdade.model.Exemplo;
import com.trabalho.projetofaculdade.repository.ExemploRepository;
import com.trabalho.projetofaculdade.service.ExemploService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExemploServiceImpl implements ExemploService {

    private final ExemploRepository exemploRepository;

    @Override
    public List<ExemploResponseDTO> listarTodos() {
        return exemploRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExemploResponseDTO buscarPorId(Long id) {
        Exemplo exemplo = exemploRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Exemplo não encontrado com id: " + id));
        return toResponseDTO(exemplo);
    }

    @Override
    public ExemploResponseDTO criar(ExemploRequestDTO dto) {
        Exemplo exemplo = Exemplo.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();
        return toResponseDTO(exemploRepository.save(exemplo));
    }

    @Override
    public ExemploResponseDTO atualizar(Long id, ExemploRequestDTO dto) {
        Exemplo exemplo = exemploRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Exemplo não encontrado com id: " + id));
        exemplo.setNome(dto.getNome());
        exemplo.setDescricao(dto.getDescricao());
        return toResponseDTO(exemploRepository.save(exemplo));
    }

    @Override
    public void deletar(Long id) {
        if (!exemploRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Exemplo não encontrado com id: " + id);
        }
        exemploRepository.deleteById(id);
    }

    private ExemploResponseDTO toResponseDTO(Exemplo exemplo) {
        return ExemploResponseDTO.builder()
                .id(exemplo.getId())
                .nome(exemplo.getNome())
                .descricao(exemplo.getDescricao())
                .build();
    }
}
