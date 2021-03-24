package com.undeadbigunicorn.demo.repository;

import com.undeadbigunicorn.demo.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Optional<BookEntity> findById(final Integer id);

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findByIsbn(final String isbn);

    Optional<BookEntity> findByTitle(final String title);

    @Query(value = "FROM BookEntity where isbn like :keyword or title like :keyword",
    countQuery = "select count(*) FROM BookEntity where isbn like :keyword or title like :keyword")
    Page<BookEntity> findByKeyword(@Param("keyword") final String keyword, Pageable pageable);

}
