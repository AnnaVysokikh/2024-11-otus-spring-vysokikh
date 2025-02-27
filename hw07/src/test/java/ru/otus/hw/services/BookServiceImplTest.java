package ru.otus.hw.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис работы с книгами должен ")
@DataJpaTest
@Transactional(propagation = Propagation.NEVER)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import({BookServiceImpl.class})
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    private static Book getBookData() {
        return new Book(1L, "BookTitle_1", getAuthorData(), getGenreData());
    }

    private static Book getNewBookData() {
        return new Book(4L, "NewBookTitle_1", getAuthorData(), getGenreData());
    }

    private static Author getAuthorData() {
        return new Author(1L, "Author_1");
    }

    private static List<Genre> getGenreData() {
        return List.of(new Genre(1L, "Genre_1"), new Genre(2L, "Genre_2"));
    }

    private static final Author AUTHOR_1 = new Author(1, "Author_1");
    private static final Author AUTHOR_2 = new Author(2, "Author_2");
    private static final Author AUTHOR_3 = new Author(3, "Author_3");

    private static final Genre GENRE_1 = new Genre(1, "Genre_1");
    private static final Genre GENRE_2 = new Genre(2, "Genre_2");
    private static final Genre GENRE_3 = new Genre(3, "Genre_3");
    private static final Genre GENRE_4 = new Genre(4, "Genre_4");
    private static final Genre GENRE_5 = new Genre(5, "Genre_5");
    private static final Genre GENRE_6 = new Genre(6, "Genre_6");

    private static final Book BOOK_1 = new Book(1, "BookTitle_1", AUTHOR_1, List.of(GENRE_1, GENRE_2));
    private static final Book BOOK_2 = new Book(2, "BookTitle_2", AUTHOR_2, List.of(GENRE_3, GENRE_4));
    private static final Book BOOK_3 = new Book(3, "BookTitle_3", AUTHOR_3, List.of(GENRE_5, GENRE_6));

    @Test
    @DisplayName(" вернуть корректную книгу по id")
    public void shouldReturnCorrectBookById() {
        Optional<Book> actualBook = bookService.findById(1L);
        var expectedBook = getBookData();

        assertThat(actualBook).isPresent();

        assertThat(actualBook.get()).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("должен загружать список всех книг")
    @Test
    void shouldReturnCorrectBooksList() {
        var actualBooks = bookService.findAll();
        List<Book> expectedBooks = List.of(BOOK_1, BOOK_2, BOOK_3);

        assertThat(actualBooks).isNotEmpty()
                .hasSize(3)
                .hasOnlyElementsOfType(Book.class)
                .usingRecursiveComparison()
                .isEqualTo(expectedBooks);
    }

    @DisplayName("должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        var expectedBook = getNewBookData();
        var actualBook = bookService.create(expectedBook.getTitle(), expectedBook.getAuthor().getId(),
                expectedBook.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));
        assertThat(actualBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("должен сохранять измененную книгу")
    @Test
    void shouldSaveUpdatedBook() {
        var expectedBook = getBookData();
        expectedBook.setTitle("Change_Title_1");

        assertThat(bookService.findById(expectedBook.getId()))
                .isPresent();

        assertThat(bookService.findById(expectedBook.getId()).get().getTitle()).isNotEqualTo(expectedBook.getTitle());

        var actualBook = bookService.update(1L, expectedBook.getTitle(), expectedBook.getAuthor().getId(),
                expectedBook.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));
        assertThat(actualBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().isEqualTo(expectedBook);

        assertThat(bookService.findById(actualBook.getId()))
                .isPresent();
    }

    @DisplayName("должен удалять книгу по id ")
    @Test
    void shouldDeleteBook() {
        assertThat(bookService.findById(1L)).isPresent();
        bookService.deleteById(1L);

        var actualBook = bookService.findById(1L);
        assertThat(actualBook).isEmpty();
    }

}