package com.undeadbigunicorn.demo.service;

import com.undeadbigunicorn.demo.dto.BookAddRequestDto;
import com.undeadbigunicorn.demo.repository.BookRepository;
import com.undeadbigunicorn.demo.repository.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookEntity> listBooks() {
        return bookRepository.findAll();
    }

    public List<BookEntity> listBooks(final String keyword) {
        return bookRepository.findByKeyword("%" + keyword + "%");
    }

    @Transactional
    public void saveBook(final BookEntity book) {
        bookRepository.saveAndFlush(book);
        log.info(String.format("Added new book #%d", book.getId()));
    }

    public Optional<BookEntity> getBookByIsbn(final String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Optional<BookEntity> getBookByTitle(final String title) {
        return bookRepository.findByTitle(title);
    }
}
