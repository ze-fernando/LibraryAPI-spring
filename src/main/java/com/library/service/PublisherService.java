package com.library.service;

import com.library.domain.Publisher;
import com.library.models.ResponseJson;
import com.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository repository;

    public List<Publisher> getAll(){
        return repository.findAll();
    }

    public Optional<Publisher> getById(Long id){
        var list = repository.findById(id);
        if (list.isEmpty()) ResponseJson.message("Id does not exists", HttpStatus.NOT_FOUND);
        return repository.findById(id);
    }

    public ResponseEntity<Object> save(Publisher p){
        var list = repository.findByName(p.getName());
        if (!list.isEmpty()) return ResponseJson
                .message("Publisher already exists", HttpStatus.BAD_REQUEST);
        repository.save(p);
        return ResponseJson.message("Successful", HttpStatus.OK);
    }

    public ResponseEntity<Object> delete(Long id){
        repository.deleteById(id);
        return ResponseJson.message("Successful", HttpStatus.OK);
    }

}
