package ru.otus.hw.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.hw.models.Book;
import ru.otus.hw.repositories.CommentRepository;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id = UUID.fromString(source.get("_id").toString());
        commentRepository.deleteAllByBookId(id);
    }
}
