package com.example.book.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestBook {

    @Test
    public void testBookGettersAndSetters() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Title");
        book.setAuthor("Test Author");

        assertEquals(1L, book.getId());
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
    }
}
