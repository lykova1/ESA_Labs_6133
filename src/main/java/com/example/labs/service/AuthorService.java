package com.example.labs.service;

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

    public List<AuthorFormDto> findAll() {
        return authorRepo.findAll().stream()
                .map(a -> new AuthorFormDto(a.getId(), a.getFirstName(), a.getLastName(), a.getBirthYear(), a.getCountry()))
                .toList();
    }
    public AuthorFormDto create(AuthorFormDto dto){
        Author author = new Author(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthYear(),
                dto.getCountry()
        );

        Author saved = authorRepo.save(author);

        return new AuthorFormDto(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getBirthYear(),
                saved.getCountry()
        );
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

    public AuthorFormDto update(Long id, AuthorFormDto dto) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBirthYear(dto.getBirthYear());
        author.setCountry(dto.getCountry());

        Author saved = authorRepo.save(author);

        return new AuthorFormDto(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getBirthYear(),
                saved.getCountry()
        );
    }

    public void deleteById(Long id) {
        if (!authorRepo.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        authorRepo.deleteById(id);
    }
}
