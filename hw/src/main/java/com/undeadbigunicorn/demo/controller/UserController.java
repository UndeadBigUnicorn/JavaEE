package com.undeadbigunicorn.demo.controller;

import com.undeadbigunicorn.demo.domain.entities.BookEntity;
import com.undeadbigunicorn.demo.domain.entities.UserEntity;
import com.undeadbigunicorn.demo.domain.exceptions.NotFoundException;
import com.undeadbigunicorn.demo.domain.helpers.RequestHelper;
import com.undeadbigunicorn.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RequestHelper requestHelper;

    @PreAuthorize("hasAuthority('VIEW_FAVOURITES')")
    @SneakyThrows
    @ResponseBody
    @GetMapping("/favourite-books")
    public List<BookEntity> getAllFavouriteBooks(HttpServletRequest request) {
        UserEntity user = getUser(request, "No books for this user");
        return userService.getFavouriteBooks(user);
    }

    @PreAuthorize("hasAuthority('ADD_FAVOURITE')")
    @SneakyThrows
    @ResponseBody
    @PostMapping("/favourite-books/{bookId}")
    public List<BookEntity> addBookToFavourites(@PathVariable Integer bookId, HttpServletRequest request) {
        UserEntity user = getUser(request, "Cannot add book to favourites since current user does not exist");

        return userService.addBookToFavourites(user, bookId);
    }

    @PreAuthorize("hasAuthority('REMOVE_FAVOURITE')")
    @SneakyThrows
    @DeleteMapping("/favourite-books/{bookId}")
    public List<BookEntity> removeBookFromFavourites(@PathVariable Integer bookId, HttpServletRequest request) {
        UserEntity user = getUser(request, "Cannot remove book from favourites since current user does not exist");

        return userService.removeBookFromFavourites(user, bookId);
    }

    private UserEntity getUser(HttpServletRequest request, final String exceptionMessage) {
        return userService.
                findByUserId(requestHelper.getCurrentUser(request).getId())
                .orElseThrow(new NotFoundException(exceptionMessage));
    }


}
