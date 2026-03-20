package com.trabalho.projetofaculdade.repository;

import com.trabalho.projetofaculdade.model.Exemplo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemploRepository extends JpaRepository<Exemplo, Long> {
}
