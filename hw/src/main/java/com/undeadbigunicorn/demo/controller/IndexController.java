package com.undeadbigunicorn.demo.controller;

import com.undeadbigunicorn.demo.domain.helpers.RequestHelper;
import com.undeadbigunicorn.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BookService bookService;
    private final RequestHelper requestHelper;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("isLoggedIn", requestHelper.isUserLoggedId(request));
        return "index";
    }

    @GetMapping("/book/{isbn}")
    public String getBook(@PathVariable("isbn") final String isbn, Model model) {
        return bookService.getBookByIsbn(isbn)
                .map((book) -> {model.addAttribute("book", book); return "book";})
                .orElse("404");
    }
}
