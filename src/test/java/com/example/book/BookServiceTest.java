package com.example.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.book.service.BookService;
import com.example.book.repository.BookRepository;
import com.example.book.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Author");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book created = bookService.addBook(book);

        assertNotNull(created);
        assertEquals("Test Book", created.getTitle());
        verify(bookRepository).save(book);
    }

    @Test
    public void whenBookIdGiven_thenBookShouldBeFound() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("The Hobbit");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> found = bookService.findById(1L);

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("The Hobbit");
    }
}

