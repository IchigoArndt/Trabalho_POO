package com.trabalho.projetofaculdade.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErroDetalhe {

    private int status;
    private String erro;
    private String mensagem;
    private LocalDateTime timestamp;
    private List<String> detalhes;
}
