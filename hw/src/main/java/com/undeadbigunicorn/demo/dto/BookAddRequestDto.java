package com.undeadbigunicorn.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class BookAddRequestDto {
    private final String title;
    private final String isbn;
    private final String author;
}
