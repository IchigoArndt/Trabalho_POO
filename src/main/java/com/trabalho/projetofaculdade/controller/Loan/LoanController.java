package com.trabalho.projetofaculdade.controller.Loan;

import com.trabalho.projetofaculdade.dto.Loan.LoanRequestDTO;
import com.trabalho.projetofaculdade.dto.Loan.LoanResponseDTO;
import com.trabalho.projetofaculdade.service.Book.BookService;
import com.trabalho.projetofaculdade.service.Loan.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/borrow")
    public ResponseEntity<LoanResponseDTO> borrow(@RequestBody @Valid LoanRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.borrow(dto));
    }
    @PatchMapping("/return/{loanId}")
    public ResponseEntity<LoanResponseDTO> returnBook(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.returnBook(loanId));
    }
}
