package com.library.domain;

import jakarta.persistence.*;
import lombok.Value;

import java.io.Serializable;

@Entity @Table(name = "publisher")
@Value
public class Publisher implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;
}
