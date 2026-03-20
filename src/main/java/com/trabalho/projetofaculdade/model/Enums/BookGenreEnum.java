package com.trabalho.projetofaculdade.model.Enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum BookGenreEnum {

    Romance(1,"Romance"),
    Fantasy(2, "Fantasia"),
    Science_Fiction(3, "Ficção Científica"),
    Thriller_Mystery(4, "Suspense / Mistério"),
    Horror(5, "Terror"),
    Biography(6,"Biografia"),
    History(7,"História"),
    Children_Books(8, "Contos de Fada");

    private final int id;
    private final String descricao;

    public static BookGenreEnum of(Integer id) {
        if (id == null) return null;
        for (BookGenreEnum c : values()) {
            if (c.id == id) return c;
        }
        return null;
    }
}
