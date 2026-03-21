package com.trabalho.projetofaculdade.model;

import com.trabalho.projetofaculdade.model.Enums.BookGenreEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Get e Setters
@Builder
@NoArgsConstructor //Construtores vazios
@AllArgsConstructor // Construtores implicitos para todas as propriedades
@Entity
@Table(name = "tbBook")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String Title;

    @Column(nullable = false)
    private int QuantityPages;

    @Column(nullable = false)
    private String Author; //Criar a classe Autor

    @Column(nullable = false)
    private String ISBN;

    @Column(nullable = false)
    private String Publisher;

    @Column(nullable = false)
    private int YearPublish;

    @Column(nullable = false)
    private BookGenreEnum Category;

    @Column(nullable = false)
    private Boolean Available = true;
}
