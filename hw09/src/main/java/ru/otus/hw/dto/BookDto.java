package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Long id;

    @NotBlank(message = "Укажите заголовок")
    private String title;

    @NotNull(message = "Выберите автора")
    private AuthorDto authorDto;

    @NotNull(message = "Выберите жанр")
    private GenreDto genreDto;
}
