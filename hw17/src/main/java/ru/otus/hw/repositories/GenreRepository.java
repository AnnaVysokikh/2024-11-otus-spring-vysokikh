package ru.otus.hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}