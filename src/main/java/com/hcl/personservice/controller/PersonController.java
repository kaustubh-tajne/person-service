package com.hcl.personservice.controller;

import com.hcl.personservice.dto.PersonDto;
import com.hcl.personservice.model.Person;
import com.hcl.personservice.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> result = personService.getAll();
//        ResponseEntity<List<PersonDto>> responseEntity = new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
//        ResponseEntity<List<PersonDto>> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
//        ResponseEntity<List<PersonDto>> responseEntity = ResponseEntity.accepted().body(result);

        ResponseEntity<List<PersonDto>> responseEntity = ResponseEntity.ok(result);
        ResponseEntity.ok().body(result);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getOneById(@PathVariable long id) {
        LOGGER.info("GetOneById - {}", id);

        final PersonDto result = personService.getOneById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
//            throw new Exception();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@Valid @RequestBody PersonDto personDto) {
        LOGGER.info("Person Got Created - {}", personDto);

        final PersonDto result = personService.create(personDto);
        if (result == null)
            return ResponseEntity.unprocessableEntity().build();
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto personDto) {
        LOGGER.info("Person Got updated - {}", personDto);

        final PersonDto result = personService.update(personDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity()
                    .build();
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        LOGGER.info("Person Got deleted {}", id);

        final boolean isDeleted = personService.delete(id);
        if (!isDeleted) {
            // check the more perfect status code
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }
}
