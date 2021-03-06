package com.undeadbigunicorn.demo.controller;

import com.undeadbigunicorn.demo.repository.entity.BookEntity;
import com.undeadbigunicorn.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BookService bookService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/book/{isbn}")
    public String getBook(@PathVariable("isbn") final String isbn, Model model) {
        return bookService.getBookByIsbn(isbn)
                .map((book) -> {model.addAttribute("book", book); return "book";})
                .orElse("404");
    }
}
