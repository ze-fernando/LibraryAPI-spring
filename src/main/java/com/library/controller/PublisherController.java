package com.library.controller;

import com.library.domain.Publisher;
import com.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publisher/")
public class PublisherController {

    @Autowired
    private PublisherRepository repository;

    @GetMapping("/find")
    public List<Publisher> getAll(){
        List<Publisher> pubs = repository.findAll();
        return pubs;
    }
}
