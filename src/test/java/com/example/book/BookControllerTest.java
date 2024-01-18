package com.example.book;

import com.example.book.model.Book;
import com.example.book.service.BookService;
import com.example.book.controller.BookController;
import com.example.book.repository.BookRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void whenGetBookById_thenReturnsBook() throws Exception {
        Long bookId = 1234L;
        Book mockBook = new Book();
        mockBook.setId(bookId);
        mockBook.setTitle("The Hobbit");

        when(bookService.findById(bookId)).thenReturn(Optional.of(mockBook)); // Mock the service layer

        mockMvc.perform(get("/api/books/" + bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("The Hobbit")));
    }
    }
