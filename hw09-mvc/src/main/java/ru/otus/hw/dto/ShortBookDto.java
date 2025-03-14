package ru.otus.hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortBookDto {
    private long id;

    @NotBlank(message = "Укажите заголовок")
    private String title;

    @NotNull(message = "Выберите автора")
    private Long authorId;

    @NotNull(message = "Выберите жанр")
    private Long genreId;
}