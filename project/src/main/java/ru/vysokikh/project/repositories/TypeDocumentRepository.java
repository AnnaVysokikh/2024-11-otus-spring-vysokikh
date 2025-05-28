package ru.vysokikh.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vysokikh.project.models.TypeDocument;

public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
}
