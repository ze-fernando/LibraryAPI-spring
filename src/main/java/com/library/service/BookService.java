package com.library.service;

import com.library.domain.Book;
import com.library.domain.Status;
import com.library.models.ResponseJson;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Book save(Book b){
        if (b.getStatus() != Status.AVAILABLE) b.setStatus(Status.AVAILABLE);
        repository.save(b);
        return b;
    }

    public ResponseJson delete(Long id){
        repository.deleteById(id);
        return new ResponseJson("Successful");
    }

    public ResponseJson rentBook(Long id){
        Optional<Book> b = repository.findById(id);
        if (b.isEmpty()) return new ResponseJson("Id does not exists");

       return new ResponseJson("Book rented");

    }
}
