package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw.dto.BookDto;
import ru.otus.hw.dto.CreateBookDto;
import ru.otus.hw.dto.UpdateBookDto;
import ru.otus.hw.services.AuthorService;
import ru.otus.hw.services.BookService;
import ru.otus.hw.services.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String getAll(Model model) {
        List<BookDto> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        BookDto book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/books/update/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        BookDto book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "update";
    }

    @PostMapping("/books/update")
    public String save(@Valid @ModelAttribute("book") UpdateBookDto book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("authors", authorService.findAll());
            return "update";
        }

        bookService.update(book.getId(), book.getTitle(), book.getAuthorId(), book.getGenreId());
        return "redirect:/books";
    }

    @GetMapping("/books/create")
    public String create(Model model) {
        model.addAttribute("book", new BookDto());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "create";
    }

    @PostMapping("/books/create")
    public String create(@Valid @ModelAttribute("book") CreateBookDto book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("authors", authorService.findAll());
            return "create";
        }
        bookService.insert(book.getTitle(), book.getAuthorId(), book.getGenreId());
        return "redirect:/books";
    }

    @PostMapping("/books/delete")
    public String delete(@RequestParam("id") long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}

