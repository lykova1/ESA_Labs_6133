package com.example.labs.controller.rest;

import com.example.labs.dto.AuthorFormDto;
import com.example.labs.service.AuthorService;
import com.example.labs.util.XmlViewRenderer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    @Autowired
    AuthorService authorService;

    @Autowired
    XmlViewRenderer xmlViewRenderer;

    @GetMapping(produces = "application/json")
    public List<AuthorFormDto> allAuthorsJson() {
        return authorService.findAll();
    }

    @GetMapping(produces = "application/xml")
    public ResponseEntity<String> allAuthorsXml() throws Exception {
        List<AuthorFormDto> authors = authorService.findAll();
        String xml = xmlViewRenderer.render(authors, "/xsl/authors.xsl");
        return ResponseEntity.ok()
                .header("Content-Type", "application/xml; charset=UTF-8")
                .body(xml);
    }

    @PostMapping(consumes = {"application/json","application/xml"}, produces = "application/json")
    public ResponseEntity<AuthorFormDto> addAuthorJson(@RequestBody AuthorFormDto dto) throws JsonProcessingException {
        AuthorFormDto created = authorService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping(consumes = {"application/json","application/xml"}, produces = "application/xml")
    public ResponseEntity<String> addAuthorXml(@RequestBody AuthorFormDto dto) throws Exception {
        AuthorFormDto created = authorService.create(dto);
        String xml = xmlViewRenderer.render(created, "/xsl/authors.xsl");
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/xml; charset=UTF-8")
                .body(xml);
    }

    @PutMapping(value="/{id}", consumes = {"application/json","application/xml"}, produces = "application/json")
    public ResponseEntity<AuthorFormDto> editAuthorJson(@PathVariable Long id, @RequestBody AuthorFormDto dto) throws JsonProcessingException {
        AuthorFormDto updated = authorService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value="/{id}", consumes = {"application/json","application/xml"}, produces = "application/xml")
    public ResponseEntity<String> editAuthorXml(@PathVariable Long id, @RequestBody AuthorFormDto dto) throws Exception {
        AuthorFormDto updated = authorService.update(id, dto);
        String xml = xmlViewRenderer.render(updated, "/xsl/authors.xsl");
        return ResponseEntity.ok()
                .header("Content-Type", "application/xml; charset=UTF-8")
                .body(xml);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) throws JsonProcessingException {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
