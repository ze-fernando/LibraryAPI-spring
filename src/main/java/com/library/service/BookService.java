package com.library.service;

import com.library.Enums.Status;
import com.library.Enums.Type;
import com.library.domain.Book;
import com.library.domain.People;
import com.library.models.ResponseJson;
import com.library.repository.BookRepository;
import com.library.repository.PublisherRepository;
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
    @Autowired
    private PublisherRepository pubRep;

    public List<Book> getAll(){
        return repository.findAll();
    }

    public Optional<Book> getById(Long id){
        return repository.findById(id);
    }

    public ResponseEntity<Object> save(Book b, Type t){
        if (t != Type.PUBLISHER) return ResponseJson.message("Only publishers can save a book", HttpStatus.BAD_REQUEST);
        var list = repository.findByName(b.getName());
        if (!list.isEmpty()) return ResponseJson.message("The book already exists", HttpStatus.BAD_REQUEST);
        b.setStatus(Status.AVAILABLE);
        var publi = pubRep.findByName(b.getPublisher().getName());
        if (publi.isEmpty()) return ResponseJson.message(
                "Publisher invalid, create publisher before create book", HttpStatus.BAD_REQUEST);
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
