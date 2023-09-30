package com.library.service;

import com.library.domain.Publisher;
import com.library.models.ResponseJson;
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

    public ResponseJson save(Publisher p){
        Optional<Publisher> list = repository.findById(p.getId());
        if (list.isEmpty()) return new ResponseJson("Id does not exists");
        repository.save(p);
        return new ResponseJson("Successful");
    }

    public ResponseJson delete(Long id){
        repository.deleteById(id);
        return new ResponseJson("Successful");
    }

}
