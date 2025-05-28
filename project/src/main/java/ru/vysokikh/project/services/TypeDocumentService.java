package ru.vysokikh.project.services;

import ru.vysokikh.project.dto.TypeDocumentDto;
import ru.vysokikh.project.models.TypeDocument;

import java.util.List;

public interface TypeDocumentService {

    List<TypeDocumentDto> findAllTypeDocuments();

    TypeDocument insertTypeDocum(String typedocname);
}
