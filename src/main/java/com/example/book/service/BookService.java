package com.example.book.service;

import com.example.book.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookService {
    private final List<Book> books = new ArrayList<>();

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findById(long id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(long id, Book book) {
        findById(id).ifPresent(b -> {
            b.setTitle(book.getTitle());
            b.setAuthor(book.getAuthor());
        });
    }

    public void deleteBook(long id) {
        books.removeIf(b -> b.getId() == id);
    }

    public boolean existsById(long id) {
        return books.stream().noneMatch(b -> b.getId() == id);
    }
}


