package ru.otus.hw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthorDto {
    private Long id;

    private String fullName;
}