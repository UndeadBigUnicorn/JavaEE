package com.undeadbigunicorn.demo.controller;

import com.undeadbigunicorn.demo.controller.model.Book;
import com.undeadbigunicorn.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String bookList(Model model) {
        model.addAttribute("books", bookService.allBooks());
        return "index";
    }

    @PostMapping("/add-book")
    public String addNewBook(@ModelAttribute("book") Book book, BindingResult result) {
        System.out.println(result.getAllErrors());
        if (result.hasErrors()) {
            return "error";
        }
        bookService.addBook(book);
        return "redirect:/";
    }
}
