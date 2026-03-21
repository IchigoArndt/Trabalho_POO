package com.trabalho.projetofaculdade.service.Loan;

import com.trabalho.projetofaculdade.dto.Loan.LoanRequestDTO;
import com.trabalho.projetofaculdade.dto.Loan.LoanResponseDTO;

public interface LoanService {
     LoanResponseDTO borrow(LoanRequestDTO dto);
     LoanResponseDTO returnBook(Long loanId);
}
