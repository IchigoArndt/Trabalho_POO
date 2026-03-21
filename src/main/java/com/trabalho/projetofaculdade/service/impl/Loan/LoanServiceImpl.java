package com.trabalho.projetofaculdade.service.impl.Loan;

import com.trabalho.projetofaculdade.dto.Loan.LoanRequestDTO;
import com.trabalho.projetofaculdade.dto.Loan.LoanResponseDTO;
import com.trabalho.projetofaculdade.exception.RecursoNaoEncontradoException;
import com.trabalho.projetofaculdade.model.Book;
import com.trabalho.projetofaculdade.model.Enums.LoanStatusEnum;
import com.trabalho.projetofaculdade.model.Loan;
import com.trabalho.projetofaculdade.model.User;
import com.trabalho.projetofaculdade.repository.Book.BookRepository;
import com.trabalho.projetofaculdade.repository.Loan.LoanRepository;
import com.trabalho.projetofaculdade.repository.User.UserRepository;
import com.trabalho.projetofaculdade.service.Loan.LoanService;
import com.trabalho.projetofaculdade.service.Log.ActionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final BookRepository bookRepository;
    private final ActionLogService actionLogService;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    @Override
    public LoanResponseDTO borrow(LoanRequestDTO dto) {
        try{
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
            Book book = bookRepository.findById(dto.getBookId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado"));
            // Validação de disponibilidade
            if (!book.getAvailable()) {
                actionLogService.log("Loan", "ERROR", "Livro não está disponível para empréstimo");
                throw new RuntimeException("Livro não está disponível para empréstimo");
            }
            book.setAvailable(false);
            bookRepository.save(book);
            Loan loan = Loan.builder()
                    .user(user)
                    .book(book)
                    .loanDate(LocalDate.now())
                    .expectedReturnDate(dto.getExpectedReturnDate())
                    .status(LoanStatusEnum.ACTIVE)
                    .build();

            actionLogService.log("Loan", "BORROW", "O Emprestimo foi concluido");
            return toResponseDTO(loanRepository.save(loan));
        } catch (Exception e) {
            actionLogService.log("Loan", "ERROR", "Ocorreu um erro inesperado ao registrar um novo emprestimo.\n Stack Trace: " + e.getStackTrace() + "Mensagem:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public LoanResponseDTO returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> {
            actionLogService.log("Loan", "ERROR", "Empréstimo não encontrado com id: " + loanId);
            return new RecursoNaoEncontradoException("empréstimo não encontrado com id: " + loanId);
        });
        if (loan.getStatus() == LoanStatusEnum.RETURNED) {
            actionLogService.log("Loan", "ERROR", "Este empréstimo já foi devolvido");
            throw new RuntimeException("Este empréstimo já foi devolvido");
        }
        loan.setActualReturnDate(LocalDate.now());
        loan.setStatus(LoanStatusEnum.RETURNED);
        loan.getBook().setAvailable(true);
        bookRepository.save(loan.getBook());
        return toResponseDTO(loanRepository.save(loan));
    }

    private LoanResponseDTO toResponseDTO(Loan loan) {
        return LoanResponseDTO.builder()
                .Id(loan.getId())
                .UserName(loan.getUser().getName())
                .BookTitle(loan.getBook().getTitle())
                .LoanDate(loan.getLoanDate())
                .ExpectedReturnDate(loan.getExpectedReturnDate())
                .ActualReturnDate(loan.getActualReturnDate())
                .Status(loan.getStatus())
                .build();
    }
}
