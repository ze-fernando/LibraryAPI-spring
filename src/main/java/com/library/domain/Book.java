package com.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "books")
@Data
@NoArgsConstructor(force = true)
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    String name;

    String author;

    Integer year;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    Publisher publisher;

}
