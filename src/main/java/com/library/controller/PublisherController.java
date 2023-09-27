package com.library.controller;

import com.library.domain.Publisher;
import com.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/publisher/")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/find")
    List<Publisher> ListAll(){
        return publisherService.getAll();
    }

    @GetMapping("/find/{id}")
    Optional<Publisher> listById(@PathVariable("id") Long id){
        return publisherService.getById(id);
    }

}
