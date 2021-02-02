package com.undeadbigunicorn.demo.service;

import com.undeadbigunicorn.demo.controller.model.Book;
import com.undeadbigunicorn.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> allBooks() {
        return bookRepository.allBooks();
    }

    public void addBook(final Book book) {
        bookRepository.saveNewBook(book);
    }
}
