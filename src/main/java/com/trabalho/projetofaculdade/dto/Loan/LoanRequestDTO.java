package com.trabalho.projetofaculdade.dto.Loan;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanRequestDTO {
    @NotNull
    private Long userId;
    @NotNull
    private Long bookId;
    @NotNull
    private LocalDate expectedReturnDate;
}
