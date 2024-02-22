package com.hcl.personservice.dao.service;

import com.hcl.personservice.model.Person;
import com.hcl.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDaoService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Optional<Person> getOneById(long id) {
        return personRepository.findById(id);
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public boolean delete(long id) {
        personRepository.deleteById(id);
        return true;
    }

}
