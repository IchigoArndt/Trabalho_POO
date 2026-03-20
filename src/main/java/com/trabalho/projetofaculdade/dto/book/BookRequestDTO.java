package com.trabalho.projetofaculdade.dto.book;

import com.trabalho.projetofaculdade.model.Enums.BookGenreEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequestDTO {

    @NotBlank(message = "O Titulo do livro é obrigatório")
    @Size(min = 10, max = 100, message = "O Titulo do livro é obrigatório")
    private String title;

    @Min(value = 10,message = "O Livro não poder conter menos que 10 páginas")
    @Max(value = 3300, message = "O Livro não pode conter mais que 3300 páginas")
    private int QuantityPages;

    @NotBlank(message = "O Autor do livro é obrigatório")
    private String Author;

    @NotBlank(message = "O ISBN é obrigatório")
    private String ISBN;

    @NotBlank(message = "O nome da editora é obrigatório")
    private String Publisher;

    private int YearPublish;

    private BookGenreEnum Category;
}
