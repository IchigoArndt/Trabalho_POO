package com.trabalho.projetofaculdade.dto.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDTO {
    private Long Id;
    private String Title;
    private int QuantityPages;
    private String Author;
    private Boolean Available;
}
