package com.hcl.personservice.dao.service;

import com.hcl.personservice.model.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDaoService {
    private List<Person> people = new ArrayList<>(
            Arrays.asList(
                    new Person(1001, "A", "AA", LocalDate.now()),
                    new Person(1002, "B", "BB", LocalDate.now()),
                    new Person(1003, "C", "CC", LocalDate.now())
            )
    );

    public List<Person> getAll() {
        return people;
    }

    public Person getOneById(long id) {
        final Optional<Person> optionalPerson = people.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (optionalPerson.isPresent()) return optionalPerson.get();
        return null;
    }

    public Person create(Person person) {
        people.add(person);

        return getOneById(person.getId());
    }

    public Person update(Person person) {
        delete(person.getId());

        return create(person);
    }

    public boolean delete(long id) {
        return people.removeIf(p -> p.getId() == id);
    }

}
