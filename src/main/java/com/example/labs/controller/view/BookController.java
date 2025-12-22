package com.example.labs.controller.view;

import com.example.labs.dto.BookCreateDto;
import com.example.labs.dto.BookUpdateDto;
import com.example.labs.service.AuthorService;
import com.example.labs.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;

    @GetMapping
    public String getAllBooks(Model model){
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("activePage", "books");
        return "books";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model){
        model.addAttribute("book", new BookCreateDto());
        model.addAttribute("authors", authorService.findAll());
        return "book-add";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") BookCreateDto dto) throws JsonProcessingException {
        bookService.create(dto);
        return ("redirect:/books");
    }

    @PostMapping("/{id}/edit")
    public String updateBook(@PathVariable Long id, BookUpdateDto dto) throws JsonProcessingException {
        bookService.update(dto, id);
        return ("redirect:/books");
    }

    @GetMapping("/{id}/edit")
    public String showUpdateBookForm(@PathVariable Long id, Model model){
        BookUpdateDto dto = bookService.findByIdForEdit(id);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", dto);
        return "book-update";
    }

    @PostMapping("/{id}/delete")
    public  String deleteBook(@PathVariable Long id) throws JsonProcessingException {
        bookService.deleteBookByID(id);
        return ("redirect:/books");
    }
}
