package com.undeadbigunicorn.demo.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class BookDto {
    @NotNull(message = "Title cannot be empty")
    @Size(min = 1, max = 200)
    private String title;

    @NotNull(message = "Author cannot be empty")
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[a-zA-Z.']+ [a-zA-Z.']+( [a-zA-Z.']+)?( [a-zA-Z.']+)?$",
            message = "Author name should consist of two to four words." +
                    "Words can contain letters, dots and apostrophes")
    private String author;

    @NotNull(message = "ISBN cannot be empty")
    @Pattern(regexp = "^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$", message = "ISBN should consist of 13 digits")
    private String isbn;
}
