package com.example.labs.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookCreateDto {

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

    public BookCreateDto() {
    }

    public BookCreateDto(String title, String isbn, Integer publishYear,
                         Integer pages, String genre, Long authorId) {
        this.title = title;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.pages = pages;
        this.genre = genre;
        this.authorId = authorId;
    }
}
