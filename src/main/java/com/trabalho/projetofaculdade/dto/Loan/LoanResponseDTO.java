package com.trabalho.projetofaculdade.dto.Loan;

import com.trabalho.projetofaculdade.model.Enums.LoanStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LoanResponseDTO {
    private Long Id;
    private String UserName;
    private String BookTitle;
    private LocalDate LoanDate;
    private LocalDate ExpectedReturnDate;
    private LocalDate ActualReturnDate;
    private LoanStatusEnum Status;
}
