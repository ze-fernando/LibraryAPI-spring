package com.library.controller;

import com.library.domain.Publisher;
import com.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/publisher/")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/find")
    List<Publisher> listAll(){
        return publisherService.getAll();
    }

    @GetMapping("/find/{id}")
    Optional<Publisher> listById(@PathVariable("id") Long id){
        return publisherService.getById(id);
    }

    @PostMapping("/save")
    ResponseEntity<Object> savePublisher(@RequestBody Publisher p){
        return publisherService.save(p);
    }

    @DeleteMapping("/del/{id}")
    ResponseEntity<Object> deletePublisher(@PathVariable Long id){
        return publisherService.delete(id);
    }
}
