package com.undeadbigunicorn.demo.repository;

import com.undeadbigunicorn.demo.dto.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class BookRepository {

    private static final Map<String, Book> BOOK_DATABASE = initDatabase();

    public List<Book> allBooks() {
        return new ArrayList<>(BOOK_DATABASE.values());
    }

    public Book saveNewBook(final Book book) {
        log.info("Saving new book: {}", book);

        BOOK_DATABASE.put(book.getIsbn(), book);
        return book;
    }

    private static Map<String, Book> initDatabase() {
        return new HashMap<>();
    }
}
