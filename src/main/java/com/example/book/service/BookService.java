package com.example.book.service;

import com.example.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {

        Book book = new Book();
        book.setTitle(rs.getString("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        return book;
    };

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", bookRowMapper);
    }

    public Optional<Book> findById(long id) {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books WHERE id = ?", bookRowMapper, id);
        return books.stream().findFirst();
    }

    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO books (title, author) VALUES (?, ?)", book.getTitle(), book.getAuthor());
    }

    public void updateBook(long id, Book book) {
        jdbcTemplate.update("UPDATE books SET title = ?, author = ? WHERE id = ?", book.getTitle(), book.getAuthor(), id);
    }

    public void deleteBook(long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    public boolean existsById(long id) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM books WHERE id = ?", Integer.class, id);
        return count != null && count > 0;
    }
}



