package com.undeadbigunicorn.demo.repository;

import com.undeadbigunicorn.demo.repository.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookRepository {

    private final EntityManager entityManager;

    @Transactional
    public List<BookEntity> allBooks() {
        return entityManager.createQuery("FROM BookEntity", BookEntity.class).getResultList();
    }

    @Transactional
    public BookEntity saveNewBook(final BookEntity book) {
        log.info("Saving new book: {}", book);

        return entityManager.merge(book);
    }

    @Transactional
    public BookEntity getBookByIsbn(final String isbn) {
        return entityManager.find(BookEntity.class, isbn);
    }

    @Transactional
    public List<BookEntity> findByKeyword(final String keyword) {
        return entityManager.createQuery("FROM BookEntity where isbn like :isbn or title like :title", BookEntity.class)
                .setParameter("isbn", "%"+keyword+"%")
                .setParameter("title", "%"+keyword+"%")
                .getResultList();
    }

}
