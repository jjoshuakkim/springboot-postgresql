package com.example.springboot_postgresql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_postgresql.repo.PersonRepo;
import com.example.springboot_postgresql.model.Person;

@RestController
public class PersonController {

    @Autowired
    private PersonRepo personRepo;

    @PostMapping("/addPerson")
    public void addPerson(@RequestBody Person person) {
        personRepo.save(person);
    }
}
