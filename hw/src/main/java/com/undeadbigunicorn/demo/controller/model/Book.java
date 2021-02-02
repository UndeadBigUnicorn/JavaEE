package com.undeadbigunicorn.demo.controller.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Book {
    private String title;
    private String isbn;
    private String author;
}
