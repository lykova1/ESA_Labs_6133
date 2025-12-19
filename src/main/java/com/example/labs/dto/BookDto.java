package com.example.labs.dto;

import lombok.Data;

@Data
public class BookDto {

    private Long id;
    private String title;
    private String genre;
    private Integer publishYear;
    private Integer pages;
    private String isbn;

    private String authorFullName;


    public BookDto() {
    }

    public BookDto(Long id, String title, String genre,
                   Integer publishYear, Integer pages, String isbn,
                   String authorFullName) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publishYear = publishYear;
        this.pages = pages;
        this.isbn = isbn;
        this.authorFullName = authorFullName;
    }

}
