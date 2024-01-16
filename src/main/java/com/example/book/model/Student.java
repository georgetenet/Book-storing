package com.example.book.model;

import com.example.book.model.Book;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter private long StudentID;
    @Setter private String FirstName;
    @Setter private String Surname;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Book> booksOwned = new HashSet<>();
}
