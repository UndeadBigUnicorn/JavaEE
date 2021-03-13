package com.undeadbigunicorn.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.undeadbigunicorn.demo.dto.BookResponseDto;
import com.undeadbigunicorn.demo.repository.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldAddBook() throws Exception {
        final BookEntity book = new BookEntity(1, "11234-awdawd", "Dune", "Frank Herbert");
        final String jsonRequest = objectMapper.writeValueAsString(book);
        final String expectedResponse = objectMapper.writeValueAsString(BookResponseDto.of("Dune", "success"));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));

        final ArrayList<BookEntity> books = new ArrayList<>();
        books.add(book);
        final String expectedBooksResponse = objectMapper.writeValueAsString(books);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books?keyword=Dune")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedBooksResponse));
    }

}
