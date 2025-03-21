package ru.otus.hw.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.otus.hw.dto.AuthorDto;
import ru.otus.hw.dto.BookDto;
import ru.otus.hw.dto.CommentDto;
import ru.otus.hw.dto.GenreDto;

import java.util.List;
import java.util.stream.LongStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtils {

    public static List<AuthorDto> getAuthorDtos() {
        return LongStream.range(1, 4).boxed()
                .map(id -> new AuthorDto(id, "Author_" + id))
                .toList();
    }

    public static List<GenreDto> getGenreDtos() {
        return LongStream.range(1, 4).boxed()
                .map(id -> new GenreDto(id, "Genre_" + id))
                .toList();
    }


    public static List<BookDto> getBookDtos(List<AuthorDto> dbAuthors, List<GenreDto> dbGenres) {
        return LongStream.range(1, 4).boxed()
                .map(id -> new BookDto(id, "BookTitle_" + id, dbAuthors.get(Math.toIntExact(id - 1)), dbGenres.get(Math.toIntExact(id - 1))))
                .toList();
    }

    public static List<BookDto> getBookDtos() {
        List<AuthorDto> dbAuthors = LongStream.range(1, 4).boxed()
                .map(id -> new AuthorDto(id, "Author_" + id))
                .toList();
        var dbGenres = getGenreDtos();
        return getBookDtos(dbAuthors, dbGenres);
    }

    public static List<CommentDto> getCommentDtos() {
        List<BookDto> dbBooks = getBookDtos();
        return getCommentDtos(dbBooks);
    }

    public static List<CommentDto> getCommentDtos(List<BookDto> dbBooks) {
        return LongStream.range(1, 4).boxed()
                .map(id -> new CommentDto(id, "Message_" + id, dbBooks.get(Math.toIntExact(id - 1))))
                .toList();
    }
}