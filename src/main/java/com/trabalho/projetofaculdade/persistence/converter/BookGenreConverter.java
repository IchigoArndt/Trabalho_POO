package com.trabalho.projetofaculdade.persistence.converter;

import com.trabalho.projetofaculdade.model.Enums.BookGenreEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true) // Aplica automaticamente em todos os campos 'Categoria'
public class BookGenreConverter implements AttributeConverter<BookGenreEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookGenreEnum categoria) {
        return (categoria == null) ? null : categoria.getId();
    }

    @Override
    public BookGenreEnum convertToEntityAttribute(Integer dbData) {
        return BookGenreEnum.of(dbData);
    }
}
