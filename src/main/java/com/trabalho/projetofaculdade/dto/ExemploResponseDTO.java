package com.trabalho.projetofaculdade.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExemploResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
}
