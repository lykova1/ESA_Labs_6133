package com.example.labs.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorFormDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Integer birthYear;

    private String country;

    public AuthorFormDto() {
    }

    public AuthorFormDto(Long id, String firstName, String lastName,
                         Integer birthYear, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.country = country;
    }
}
