package com.undeadbigunicorn.demo.service;

import com.undeadbigunicorn.demo.repository.BookRepository;
import com.undeadbigunicorn.demo.repository.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookEntity> listBooks() {
        return bookRepository.allBooks();
    }

    public List<BookEntity> listBooks(String keyword) {
        return bookRepository.findByKeyword(keyword);
    }

    public void addBook(final BookEntity book) {
        bookRepository.saveNewBook(book);
    }

    public Optional<BookEntity> getBookByIsbn(final String isbn) {
        return Optional.ofNullable(bookRepository.getBookByIsbn(isbn));
    }
}
