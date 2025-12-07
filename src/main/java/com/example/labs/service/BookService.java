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
    public void create(BookCreateDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setGenre(dto.getGenre());
        book.setPublishYear(dto.getPublishYear());
        book.setPages(dto.getPages());
        book.setAuthor(authorRepo.findById(dto.getAuthorId()).orElseThrow());
        bookRepo.save(book);
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

    public void update(BookUpdateDto dto, Long id){
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
        bookRepo.save(book);
    }
    public void deleteBookByID(Long id){
        bookRepo.deleteById(id);
    }
    public BookUpdateDto findByIdForEdit(Long id){
        Book book = bookRepo.findById(id).orElseThrow();
        return new BookUpdateDto(book.getId(),book.getTitle(),book.getIsbn(),book.getPublishYear(),book.getPages(),book.getGenre(),book.getAuthor().getId());
    }
}
