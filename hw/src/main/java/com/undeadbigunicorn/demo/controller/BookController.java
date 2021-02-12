package com.undeadbigunicorn.demo.controller;

import com.undeadbigunicorn.demo.dto.Book;
import com.undeadbigunicorn.demo.dto.BookResponseDto;
import com.undeadbigunicorn.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @ResponseBody
    @GetMapping("/books")
    public List<Book> bookList(@RequestParam(name = "keyword") Optional<String> keyword) {
        return keyword.map(bookService::listBooks).orElseGet(bookService::listBooks);
    }

    @ResponseBody
    @PostMapping("/books")
    public ResponseEntity<BookResponseDto> addNewBook(@RequestBody final Book book) {
        bookService.addBook(book);
        return ResponseEntity.status(201)
                .body(BookResponseDto.of(book.getTitle(), "success"));
    }
}
