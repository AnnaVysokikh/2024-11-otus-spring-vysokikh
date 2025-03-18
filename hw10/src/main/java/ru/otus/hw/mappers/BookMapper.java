package ru.otus.hw.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.dto.BookDto;
import ru.otus.hw.models.Book;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final AuthorMapper authorMapper;

    private final GenreMapper genreMapper;

    public BookDto convertToBookDto(Book book) {
        return BookDto
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorDto(authorMapper.convertToAuthorDto(book.getAuthor()))
                .genreDto(genreMapper.convertToGenreDto(book.getGenre()))
                .build();
    }

    public Book convertToEntity(BookDto bookDto) {
        return Book
                .builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(authorMapper.convertToEntity(bookDto.getAuthorDto()))
                .genre(genreMapper.convertToEntity(bookDto.getGenreDto()))
                .build();
    }
}