package com.undeadbigunicorn.demo.service;

import com.undeadbigunicorn.demo.domain.entities.BookEntity;
import com.undeadbigunicorn.demo.domain.entities.PermissionEntity;
import com.undeadbigunicorn.demo.domain.entities.UserEntity;
import com.undeadbigunicorn.demo.domain.exceptions.NotFoundException;
import com.undeadbigunicorn.demo.repository.BookRepository;
import com.undeadbigunicorn.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public void addNewUser(UserEntity user) {
        userRepository.saveAndFlush(user);
        log.info("Added new user #" + user.getId().toString());
    }

    public List<BookEntity> getFavouriteBooks(final UserEntity user) {
        return user.getFavouriteBooks();
    }

    @SneakyThrows
    public List<BookEntity> addBookToFavourites(final UserEntity user, final Integer bookId) {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("No book found"));

        List<BookEntity> favouriteBooks = user.getFavouriteBooks();

        favouriteBooks.add(book);
        user.setFavouriteBooks(favouriteBooks);

        userRepository.saveAndFlush(user);

        log.info("User #" + user.getId().toString() + " added new book #" + book.getId().toString() + " to favourites");

        return favouriteBooks;
    }

    @SneakyThrows
    public List<BookEntity> removeBookFromFavourites(final UserEntity user, final Integer bookId) {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("No book found"));
        List<BookEntity> favouriteBooks = user.getFavouriteBooks();

        favouriteBooks.removeIf((b) -> b.getId().equals(bookId));
        user.setFavouriteBooks(favouriteBooks);

        userRepository.saveAndFlush(user);

        log.info("User #" + user.getId().toString() + " removed book #" + book.getId().toString() + " from favourites");

        return favouriteBooks;
    }

    public Optional<UserEntity> findByLogin(final String login) throws NotFoundException {
        return userRepository.findByLogin(login);
    }

    public Optional<UserEntity> findByUserId(final Integer id) throws NotFoundException {
        return userRepository.findById(id);
    }

    public static List<SimpleGrantedAuthority> mapAuthorities(final List<PermissionEntity> permissions) {
        return permissions.stream()
                .map(PermissionEntity::getPermission)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableList());
    }
}