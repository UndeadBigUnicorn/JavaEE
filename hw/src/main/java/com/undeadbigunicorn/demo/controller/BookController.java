package com.undeadbigunicorn.demo.controller;

import com.undeadbigunicorn.demo.dto.BookAddRequestDto;
import com.undeadbigunicorn.demo.dto.BookResponseDto;
import com.undeadbigunicorn.demo.repository.entity.BookEntity;
import com.undeadbigunicorn.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    public List<BookEntity> bookList(@RequestParam(name = "keyword") Optional<String> keyword) {
        return keyword.map(bookService::listBooks).orElseGet(bookService::listBooks);
    }

    @ResponseBody
    @PostMapping("/books")
    public ResponseEntity<BookResponseDto> addNewBook(@RequestBody final BookAddRequestDto book) {
        BookEntity newBook = BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
        bookService.saveBook(newBook);
        return ResponseEntity.status(201)
                .body(BookResponseDto.of(book.getTitle(), "success"));
    }
}
