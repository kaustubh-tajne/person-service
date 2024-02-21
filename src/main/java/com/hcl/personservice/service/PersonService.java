package com.hcl.personservice.service;

import com.hcl.personservice.dao.service.PersonDaoService;
import com.hcl.personservice.model.Person;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "singleton")
public class PersonService {

    private PersonDaoService personDaoService;

    public List<Person> getAll() {
        return personDaoService.getAll();
    }

    public Person getOneById(long id) {
        return personDaoService.getOneById(id);
    }

    public Person create(Person person) {
        return personDaoService.create(person);
    }

    public Person update(Person person) {
        return personDaoService.update(person);
    }

    public boolean delete(long id) {
       return personDaoService.delete(id);
    }

}
