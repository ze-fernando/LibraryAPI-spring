package com.library.service;

import com.library.Enums.Type;
import com.library.domain.Book;
import com.library.Enums.Status;
import com.library.domain.People;
import com.library.models.RentJson;
import com.library.models.ResponseJson;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


    public ResponseJson save(Book b){
        var list = repository.findById(b.getId());
        if (list.isEmpty()) return new ResponseJson("Id does not exists");
        if (b.getStatus() != Status.AVAILABLE) b.setStatus(Status.AVAILABLE);
        repository.save(b);
        return new ResponseJson("Save successful");
    }

    public ResponseJson delete(Long id){
        var b = repository.findById(id);
        if (b.isEmpty()) return new ResponseJson("Id does not exists");
        repository.deleteById(id);
        return new ResponseJson("Successful");
    }

    public RentJson rentBook(Long id, People p){
        var b = repository.findById(id);
        if (b.isEmpty()) return new ResponseJson("Id does not exists");
        var book = b.get();
        if (book.getStatus() == Status.RENTED) return new ResponseJson("The book already rented");
        if (p.getType() == Type.PUBLISHER) return new ResponseJson("Only clients can rent a book");
        book.setStatus(Status.RENTED);
        repository.save(book);
        return new RentJson("Book rented", LocalDateTime.now(), LocalDateTime.now());
    }

    public ResponseJson returnBook(Long id){
        var b = repository.findById(id);
        if (b.isEmpty()) return new ResponseJson("Id does not exists");
        var book = b.get();
        if (book.getStatus() == Status.AVAILABLE) return new ResponseJson("The book already in library");
        book.setStatus(Status.AVAILABLE);
        repository.save(book);
        return new ResponseJson("Book returned");
    }
}
