package ru.vysokikh.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vysokikh.project.models.Category;
import ru.vysokikh.project.models.Document;
import ru.vysokikh.project.models.PathSavePdf;
import ru.vysokikh.project.models.TypeDocument;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    private long docid;

    private String docname;

    private String docauthor;

    private String docyear;

    private String docsource;

    private String dockeyword;

    private Date docdateinput;

    private String docannotation;

    private Category category;

    private TypeDocument typeDocument;

    private PathSavePdf pathSavePdf;

    public static Document toDomainObject(DocumentDto documentDto) {
        return new Document(documentDto.getDocid(), documentDto.getDocname(), documentDto.getDocauthor(),
                documentDto.getDocyear(), documentDto.getDocsource(), documentDto.getDockeyword(),
                documentDto.getDocdateinput(), documentDto.getDocannotation(), documentDto.getCategory(),
                documentDto.getTypeDocument(), documentDto.getPathSavePdf());
    }

    public static DocumentDto toDocumentDto(Document document) {
        return new DocumentDto(document.getDocid(), document.getDocname(), document.getDocauthor(),
                document.getDocyear(), document.getDocsource(), document.getDockeyword(),
                document.getDocdateinput(), document.getDocannotation(), document.getCategory(),
                document.getTypeDocument(), document.getPathSavePdf());
    }

}
