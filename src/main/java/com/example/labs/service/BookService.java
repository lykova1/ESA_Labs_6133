package com.example.labs.service;

import com.example.labs.dto.BookCreateDto;
import com.example.labs.dto.BookDto;
import com.example.labs.dto.BookUpdateDto;
import com.example.labs.model.Book;
import com.example.labs.repository.AuthorRepo;
import com.example.labs.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepo authorRepo;
    @Autowired
    BookRepo bookRepo;
    public BookDto create(BookCreateDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setGenre(dto.getGenre());
        book.setPublishYear(dto.getPublishYear());
        book.setPages(dto.getPages());
        book.setAuthor(
                authorRepo.findById(dto.getAuthorId())
                        .orElseThrow(() -> new RuntimeException("Author not found"))
        );

        Book saved = bookRepo.save(book);

        return new BookDto(
                saved.getId(),
                saved.getTitle(),
                saved.getGenre(),
                saved.getPublishYear(),
                saved.getPages(),
                saved.getIsbn(),
                saved.getAuthor().getFirstName() + " " + saved.getAuthor().getLastName()
        );
    }

    public List<BookDto> findAll(){
        return bookRepo.findAll().stream()
                .map(b-> new BookDto(b.getId(), b.getTitle(),b.getGenre(), b.getPublishYear(), b.getPages(),b.getIsbn(),(b.getAuthor().getFirstName()+" "+b.getAuthor().getLastName())))
                .toList();
    }
    public List<BookDto> findByGenre(String genre){
        return bookRepo.findByGenre(genre).stream()
                .map(b-> new BookDto(b.getId(), b.getTitle(),b.getGenre(), b.getPublishYear(), b.getPages(),b.getIsbn(),(b.getAuthor().getFirstName()+" "+b.getAuthor().getLastName())))
                .toList();
    }

    public BookDto update(BookUpdateDto dto, Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setGenre(dto.getGenre());
        book.setPublishYear(dto.getPublishYear());
        book.setPages(dto.getPages());
        book.setAuthor(
                authorRepo.findById(dto.getAuthorId())
                        .orElseThrow(() -> new RuntimeException("Author not found"))
        );

        Book saved = bookRepo.save(book);

        return new BookDto(
                saved.getId(),
                saved.getTitle(),
                saved.getGenre(),
                saved.getPublishYear(),
                saved.getPages(),
                saved.getIsbn(),
                saved.getAuthor().getFirstName() + " " + saved.getAuthor().getLastName()
        );
    }

    public void deleteBookByID(Long id){
        if (!bookRepo.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepo.deleteById(id);
    }
    public BookUpdateDto findByIdForEdit(Long id){
        Book book = bookRepo.findById(id).orElseThrow();
        return new BookUpdateDto(book.getId(),book.getTitle(),book.getIsbn(),book.getPublishYear(),book.getPages(),book.getGenre(),book.getAuthor().getId());
    }
}
