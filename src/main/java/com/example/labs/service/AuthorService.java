package com.example.labs.service;

import com.example.labs.dto.AuthorDto;
import com.example.labs.dto.AuthorFormDto;
import com.example.labs.model.Author;
import com.example.labs.repository.AuthorRepo;
import com.example.labs.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorService {
    @Autowired
    AuthorRepo authorRepo;
    @Autowired
    BookRepo bookRepo;

    public List<AuthorFormDto> findAll() {
        return authorRepo.findAll().stream()
                .map(a -> new AuthorFormDto(a.getId(), a.getFirstName(), a.getLastName(), a.getBirthYear(), a.getCountry()))
                .toList();
    }
    public void create(AuthorFormDto dto){
        Author author = new Author(dto.getFirstName(), dto.getLastName(), dto.getBirthYear(), dto.getCountry());
        authorRepo.save(author);
    }

    public AuthorFormDto findByIdForEdit(Long id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        return new AuthorFormDto(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getBirthYear(),
                author.getCountry()
        );
    }

    public void update(Long id, AuthorFormDto dto) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBirthYear(dto.getBirthYear());
        author.setCountry(dto.getCountry());

        authorRepo.save(author);
    }

    public void deleteById(Long id) {
        authorRepo.deleteById(id);
    }
}
