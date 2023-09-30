package com.library.service;

import com.library.Enums.Status;
import com.library.Enums.Type;
import com.library.domain.Book;
import com.library.domain.People;
import com.library.models.ResponseJson;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getAll(){
        return repository.findAll();
    }

    public Optional<Book> getById(Long id){
        return repository.findById(id);
    }


    public ResponseEntity<Object> save(Book b){
        var list = repository.findById(b.getId());
        if (list.isEmpty()) return ResponseJson.message("Id does not exists", HttpStatus.NOT_FOUND);
        if (b.getStatus() != Status.AVAILABLE) b.setStatus(Status.AVAILABLE);
        repository.save(b);
        return ResponseJson.json("Saved successful", HttpStatus.OK, b);
    }

    public ResponseEntity<Object> delete(Long id){
        var b = repository.findById(id);
        if (b.isEmpty()) ResponseJson.message("Id does not exists", HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return ResponseJson.message("Successful", HttpStatus.OK);
    }

    public ResponseEntity<?> rentBook(Long id, People p){
        var b = repository.findById(id);
        if (b.isEmpty()) return ResponseJson.message("Id does not exists", HttpStatus.NOT_FOUND);
        var book = b.get();
        if (book.getStatus() == Status.RENTED) return ResponseJson.
                message("The book already rented", HttpStatus.BAD_REQUEST);
        if (p.getType() == Type.PUBLISHER) return ResponseJson.
                message("Only clients can rent a book", HttpStatus.BAD_REQUEST);
        book.setStatus(Status.RENTED);
        repository.save(book);
        return ResponseJson.rented("Book rented", HttpStatus.OK);
    }

    public ResponseEntity<Object> returnBook(Long id){
        var b = repository.findById(id);
        if (b.isEmpty()) ResponseJson.message("Id does not exists", HttpStatus.NOT_FOUND);
        var book = b.get();
        if (book.getStatus() == Status.AVAILABLE) return ResponseJson
                .message("The book already in library", HttpStatus.BAD_REQUEST);
        book.setStatus(Status.AVAILABLE);
        repository.save(book);
        return ResponseJson.message("Book returned", HttpStatus.OK);
    }
}
