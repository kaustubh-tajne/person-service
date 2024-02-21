package com.hcl.personservice.controller;

import com.hcl.personservice.dto.PersonDto;
import com.hcl.personservice.model.Person;
import com.hcl.personservice.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping("/api/personService/v1/persons")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class.getName());

//    @Autowired
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        System.out.println("Args constructor called");
        this.personService = personService;
    }

    @GetMapping("/data")
    public void getData(@RequestParam String name, @RequestParam int myAge) {
        LOGGER.info("getData");
    }

    @GetMapping
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDto getOneById(@PathVariable long id) {
        LOGGER.info("GetOneById - {}", id);

        return personService.getOneById(id);
    }

    @PostMapping
    public PersonDto create(@RequestBody PersonDto personDto) {
        LOGGER.info("Person Got Created - {}", personDto);

        return personService.create(personDto);
    }

    @PutMapping
    public PersonDto update(@RequestBody PersonDto personDto) {
        LOGGER.info("Person Got updated - {}", personDto);

        return personService.update(personDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        LOGGER.info("Person Got deleted {}", id);

        return personService.delete(id);
    }
}
