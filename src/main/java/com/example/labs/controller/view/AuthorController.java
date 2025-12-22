package com.example.labs.controller.view;

import com.example.labs.dto.AuthorFormDto;
import com.example.labs.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping
    public String allAuthors(Model model){
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("activePage", "authors");
        return "authors";
    }
    @PostMapping("/add")
    public String addAuthor(@ModelAttribute ("author") AuthorFormDto dto) throws JsonProcessingException {
        authorService.create(dto);
        return "redirect:/authors";
    }
    @GetMapping("/add")
    public String showAddAuthorForm(Model model){
        model.addAttribute("author", new AuthorFormDto());
        return "authors-add";
    }

    @GetMapping("/{id}/edit")
    public String showEditAuthorForm(@PathVariable Long id, Model model) {
        AuthorFormDto dto = authorService.findByIdForEdit(id);
        model.addAttribute("author", dto);
        return "authors-edit";
    }

    @PostMapping("/{id}/edit")
    public String editAuthor(@PathVariable Long id,
                             @ModelAttribute("author") AuthorFormDto dto) throws JsonProcessingException {
        authorService.update(id, dto);
        return "redirect:/authors";
    }

    @PostMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id) throws JsonProcessingException {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
