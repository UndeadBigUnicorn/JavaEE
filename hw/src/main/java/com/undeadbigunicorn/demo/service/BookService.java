package com.undeadbigunicorn.demo.service;

import com.undeadbigunicorn.demo.dto.Book;
import com.undeadbigunicorn.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> listBooks() {
        return bookRepository.allBooks();
    }

    public List<Book> listBooks(String keyword) {
        return bookRepository.allBooks()
                .stream()
                .filter(book -> book.getTitle().contains(keyword) || book.getIsbn().contains(keyword))
                .collect(Collectors.toList());
    }

    public void addBook(final Book book) {
        bookRepository.saveNewBook(book);
    }
}
