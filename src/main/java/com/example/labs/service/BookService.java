package com.example.labs.service;

import com.example.labs.dto.BookCreateDto;
import com.example.labs.dto.BookDto;
import com.example.labs.dto.BookUpdateDto;
import com.example.labs.dto.ChangeEventDto;
import com.example.labs.model.Book;
import com.example.labs.repository.AuthorRepo;
import com.example.labs.repository.BookRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final JmsTemplate jmsTemplate;
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final ObjectMapper objectMapper;

    public BookService(JmsTemplate jmsTemplate, AuthorRepo authorRepo, BookRepo bookRepo, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.objectMapper = objectMapper;
    }

    public BookDto create(BookCreateDto dto) throws JsonProcessingException {
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

        sendEvent("CREATE", saved.getId(), "Book created: " + saved.getTitle());

        return mapToDto(saved);
    }

    public BookDto update(BookUpdateDto dto, Long id) throws JsonProcessingException {
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

        sendEvent("UPDATE", saved.getId(), "Book updated: " + saved.getTitle());

        return mapToDto(saved);
    }

    public void deleteBookByID(Long id) throws JsonProcessingException {
        if (!bookRepo.existsById(id)) {
            throw new RuntimeException("Book not found");
        }

        bookRepo.deleteById(id);
        sendEvent("DELETE", id, "Book deleted");
    }

    public List<BookDto> findAll() {
        return bookRepo.findAll().stream().map(this::mapToDto).toList();
    }

    public BookUpdateDto findByIdForEdit(Long id) {
        Book book = bookRepo.findById(id).orElseThrow();
        return new BookUpdateDto(book.getId(), book.getTitle(), book.getIsbn(), book.getPublishYear(),
                book.getPages(), book.getGenre(), book.getAuthor().getId());
    }

    private BookDto mapToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getPublishYear(),
                book.getPages(),
                book.getIsbn(),
                book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName()
        );
    }

    private void sendEvent(String operation, Long id, String details) throws JsonProcessingException {
        ChangeEventDto event = new ChangeEventDto();
        event.setOperation(operation);
        event.setEntity("Book");
        event.setEntityId(id);
        event.setDetails(details);

        String json = objectMapper.writeValueAsString(event);
        jmsTemplate.convertAndSend("change.queue", json);
    }
}
