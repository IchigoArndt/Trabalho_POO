package com.trabalho.projetofaculdade.repository.Book;

import com.trabalho.projetofaculdade.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
