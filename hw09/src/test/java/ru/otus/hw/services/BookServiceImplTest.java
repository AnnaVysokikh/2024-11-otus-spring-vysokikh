package ru.otus.hw.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.*;
import ru.otus.hw.dto.*;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.mappers.*;
import ru.otus.hw.repositories.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.otus.hw.utils.TestDataUtils.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Import({BookServiceImpl.class, BookMapper.class, AuthorMapper.class, GenreMapper.class})
public class BookServiceImplTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookServiceImpl service;

    private List<AuthorDto> authorDtos;

    private List<GenreDto> genreDtos;

    private List<BookDto> bookDtos;

    @BeforeEach
    void setUp() {
        authorDtos = getAuthorDtos();
        genreDtos = getGenreDtos();
        bookDtos = getBookDtos(authorDtos, genreDtos);
    }

    @DisplayName("должен загружать книгу по id")
    @ParameterizedTest
    @MethodSource("ru.otus.hw.utils.TestDataUtils" + "#getBookDtos")
    public void findByIdTest(BookDto expectedBook) {
        var actualBook = service.findById(expectedBook.getId());

        assertThat(actualBook).isNotNull()
                .isEqualTo(expectedBook);
    }

    @DisplayName("должен загружать список всех книг")
    @Test
    void findAllTest() {
        var actualBooks = service.findAll();
        var expectedBooks = bookDtos;

        assertThat(actualBooks).containsExactlyElementsOf(expectedBooks);
        actualBooks.forEach(System.out::println);
    }

    @DisplayName("должен сохранять новую книгу")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void insertTest() {
        var expectedBook = new BookDto(4L, "BookTitle_1", authorDtos.get(0), genreDtos.get(0));
        var returnedBook = service.insert("BookTitle_1", authorDtos.get(0).getId(), genreDtos.get(0).getId());

        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        assertThat(service.findById(returnedBook.getId()))
                .isNotNull()
                .isEqualTo(returnedBook);
    }

    @DisplayName("должен не сохранять новую книгу")
    @Test
    void insertTest_NotFoundException() {
        assertThatThrownBy( () ->
        {service.insert("BookTitle_1", 5, genreDtos.get(0).getId());})
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("должен сохранять измененную книгу")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void updateTest() {
        var expectedBook = new BookDto(1L, "BookTitle_1", authorDtos.get(2), genreDtos.get(2));

        assertThat(service.findById(expectedBook.getId()))
                .isNotNull()
                .isNotEqualTo(expectedBook);

        var returnedBook = service.update(1, "BookTitle_1", authorDtos.get(2).getId(), genreDtos.get(2).getId());
        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        assertThat(service.findById(returnedBook.getId()))
                .isNotNull()
                .isEqualTo(returnedBook);
    }

    @DisplayName("должен выкидывать NotFoundException при не найденом авторе")
    @Test
    void updateTest_NoAuthorFound_NotFoundException() {
        assertThatThrownBy( () ->
        {service.update(1,"BookTitle_1", 4, 1);})
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("должен сохранять измененную книгу")
    @Test
    void updateTest_NoGenreFound_NotFoundException() {
        assertThatThrownBy( () ->
        {service.update(1,"BookTitle_1", 1, 4);})
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("должен удалять книгу по id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void shouldDeleteBook() {
        assertThat(service.findById(1L)).isNotNull();
        service.deleteById(1L);
        assertThrows(NotFoundException.class, () -> service.findById(1L));
    }
}