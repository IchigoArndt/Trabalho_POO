package com.trabalho.projetofaculdade.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExemploRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String descricao;
}
