package com.example.labs.controller.rest;

import com.example.labs.dto.BookCreateDto;
import com.example.labs.dto.BookDto;
import com.example.labs.dto.BookUpdateDto;
import com.example.labs.service.BookService;
import com.example.labs.util.XmlViewRenderer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/books")
public class RestBookController {

    @Autowired
    BookService bookService;

    @Autowired
    XmlViewRenderer xmlViewRenderer;

    @GetMapping(produces = "application/json")
    public List<BookDto> getAllBooksJson() {
        return bookService.findAll();
    }

    @GetMapping(produces = "application/xml")
    public ResponseEntity<String> getAllBooksXml() throws Exception {
        List<BookDto> books = bookService.findAll();
        String xml = xmlViewRenderer.render(books, "/xsl/books.xsl");
        return ResponseEntity.ok()
                .header("Content-Type", "application/xml; charset=UTF-8")
                .body(xml);
    }

    @PostMapping(consumes = {"application/json","application/xml"}, produces = "application/json")
    public ResponseEntity<BookDto> createJson(@RequestBody BookCreateDto dto) throws JsonProcessingException {
        BookDto created = bookService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping(consumes = {"application/json","application/xml"}, produces = "application/xml")
    public ResponseEntity<String> createXml(@RequestBody BookCreateDto dto) throws Exception {
        BookDto created = bookService.create(dto);
        String xml = xmlViewRenderer.render(created, "/xsl/books.xsl");
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/xml; charset=UTF-8")
                .body(xml);
    }

    @PutMapping(value="/{id}", consumes = {"application/json","application/xml"}, produces = "application/json")
    public ResponseEntity<BookDto> updateJson(@PathVariable Long id, @RequestBody BookUpdateDto dto) throws JsonProcessingException {
        BookDto updated = bookService.update(dto, id);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value="/{id}", consumes = {"application/json","application/xml"}, produces = "application/xml")
    public ResponseEntity<String> updateXml(@PathVariable Long id, @RequestBody BookUpdateDto dto) throws Exception {
        BookDto updated = bookService.update(dto, id);
        String xml = xmlViewRenderer.render(updated, "/xsl/books.xsl");
        return ResponseEntity.ok()
                .header("Content-Type", "application/xml; charset=UTF-8")
                .body(xml);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws JsonProcessingException {
        bookService.deleteBookByID(id);
        return ResponseEntity.noContent().build();
    }
}
