package ru.otus.hw.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.dto.CommentDto;
import ru.otus.hw.model.Comment;
import ru.otus.hw.exceptions.EntityNotFoundException;
import ru.otus.hw.mappers.CommentMapper;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    @Override
    @CircuitBreaker(name = "serviceCircuitBreaker")
    public CommentDto findById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Не удалось получить комментарий по id: %s", id)));
        return commentMapper.convertToCommentDto(comment);
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "serviceCircuitBreaker")
    public List<CommentDto> findAllByBookId(long bookId) {
        return commentRepository
                .findAllByBookId(bookId)
                .stream()
                .map(commentMapper::convertToCommentDto)
                .toList();
    }

    @Override
    @Transactional
    public CommentDto insert(String message, long bookId) {
        return save(0, message, bookId);
    }

    @Override
    @Transactional
    public CommentDto update(long id, String message, long bookId) {
        return save(id, message, bookId);
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "serviceCircuitBreaker")
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    private CommentDto save(long id, String message, long bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(bookId)));
        var comment = new Comment(id, message, book);
        return commentMapper.convertToCommentDto(commentRepository.save(comment));
    }
}