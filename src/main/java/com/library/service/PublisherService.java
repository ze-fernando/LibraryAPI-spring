package com.library.service;

import com.library.domain.Publisher;
import com.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return repository.findById(id);
    }

    public Publisher save(Publisher p){
        repository.save(p);
        return p;
    }

}
