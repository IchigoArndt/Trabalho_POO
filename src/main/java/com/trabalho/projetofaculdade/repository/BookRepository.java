package com.trabalho.projetofaculdade.repository;

import com.trabalho.projetofaculdade.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
