package com.library.domain;

import jakarta.persistence.*;
import lombok.Value;

@Entity @Table(name = "books")
@Value
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "author")
    String author;

    @Column(name = "year")
    Integer year;

    @Column(name = "status")
    Status status;

    @Column(name = "publisher_id")
    Publisher publisher;
}
