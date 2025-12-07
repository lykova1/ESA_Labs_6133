package com.example.labs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookUpdateDto {

    @NotNull
    private Long id;

    @NotBlank
    private String title;

    private String isbn;

    @Min(0)
    private Integer publishYear;

    @Min(1)
    private Integer pages;

    private String genre;

    @NotNull
    private Long authorId;

    public BookUpdateDto() {
    }

    public BookUpdateDto(Long id, String title, String isbn,
                         Integer publishYear, Integer pages,
                         String genre, Long authorId) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.pages = pages;
        this.genre = genre;
        this.authorId = authorId;
    }
}
