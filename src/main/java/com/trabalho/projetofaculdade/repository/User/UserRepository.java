package com.trabalho.projetofaculdade.repository.User;

import com.trabalho.projetofaculdade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
