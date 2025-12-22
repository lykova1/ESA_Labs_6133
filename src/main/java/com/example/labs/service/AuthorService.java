package com.example.labs.service;

import com.example.labs.dto.AuthorFormDto;
import com.example.labs.dto.ChangeEventDto;
import com.example.labs.model.Author;
import com.example.labs.repository.AuthorRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public AuthorService(AuthorRepo authorRepo, JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.authorRepo = authorRepo;
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public List<AuthorFormDto> findAll() {
        return authorRepo.findAll().stream()
                .map(a -> new AuthorFormDto(a.getId(), a.getFirstName(), a.getLastName(), a.getBirthYear(), a.getCountry()))
                .toList();
    }

    public AuthorFormDto create(AuthorFormDto dto) throws JsonProcessingException {
        Author author = new Author(dto.getFirstName(), dto.getLastName(), dto.getBirthYear(), dto.getCountry());
        Author saved = authorRepo.save(author);

        sendEvent("CREATE", saved.getId(), "Author created: " + saved.getFirstName() + " " + saved.getLastName());

        return mapToDto(saved);
    }

    public AuthorFormDto update(Long id, AuthorFormDto dto) throws JsonProcessingException {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBirthYear(dto.getBirthYear());
        author.setCountry(dto.getCountry());

        Author saved = authorRepo.save(author);

        sendEvent("UPDATE", saved.getId(), "Author updated: " + saved.getFirstName() + " " + saved.getLastName());

        return mapToDto(saved);
    }

    public void deleteById(Long id) throws JsonProcessingException {
        if (!authorRepo.existsById(id)) {
            throw new RuntimeException("Author not found");
        }
        authorRepo.deleteById(id);
        sendEvent("DELETE", id, "Author deleted");
    }

    public AuthorFormDto findByIdForEdit(Long id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return mapToDto(author);
    }

    private AuthorFormDto mapToDto(Author author) {
        return new AuthorFormDto(author.getId(), author.getFirstName(), author.getLastName(),
                author.getBirthYear(), author.getCountry());
    }

    private void sendEvent(String operation, Long id, String details) throws JsonProcessingException {
        ChangeEventDto event = new ChangeEventDto();
        event.setOperation(operation);
        event.setEntity("Author");
        event.setEntityId(id);
        event.setDetails(details);

        String json = objectMapper.writeValueAsString(event);
        jmsTemplate.convertAndSend("change.queue", json);
    }
}
