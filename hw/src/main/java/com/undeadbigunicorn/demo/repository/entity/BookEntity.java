package com.undeadbigunicorn.demo.repository.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

}