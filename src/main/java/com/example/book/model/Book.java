package com.example.book.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter private long id;
    @Setter private String title;
    @Setter private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentID")
    private Student owner;


    public void setOwner(Student student) {
    }
}