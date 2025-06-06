package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.models.Comment;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Comment findById(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAllByBookId(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Transactional
    public Comment create(String text, long bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book with id %d not found".formatted(bookId)));
        var comment = new Comment(0, text, book);

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment update(long id, String text) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment with id %d not found".formatted(id)));
        comment.setText(text);

        return commentRepository.save(comment);

    }

    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
