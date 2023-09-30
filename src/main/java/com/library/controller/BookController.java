package com.library.controller;

import com.library.domain.Book;
import com.library.domain.People;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/find")
    List<Book> listAll(){
        return bookService.getAll();
    }

    @GetMapping("/find/{id}")
    Optional<Book> listById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @PostMapping("/save")
    ResponseEntity<Object> save(@RequestBody Book b){
        return bookService.save(b);
    }

    @DeleteMapping("/del/{id}")
    ResponseEntity<Object> delete(@PathVariable Long id){
        return bookService.delete(id);
    }

    @PostMapping("/rent/{id}")
    ResponseEntity<?> rent(@PathVariable Long id, @RequestBody People p){
        return bookService.rentBook(id, p);
    }

    @PostMapping("/return/{id}")
    ResponseEntity<Object> returnBook(@PathVariable Long id){
        return bookService.returnBook(id);
    }
}
