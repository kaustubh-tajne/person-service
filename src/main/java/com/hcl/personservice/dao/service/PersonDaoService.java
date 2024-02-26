package com.hcl.personservice.dao.service;

import com.hcl.personservice.model.Address;
import com.hcl.personservice.model.Person;
import com.hcl.personservice.repository.AddressRepository;
import com.hcl.personservice.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDaoService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public PersonDaoService(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public List<Person> getAll() {
        final Person var1 = personRepository.findByFirstName("Marius");
        final List<Person> var2 = personRepository.findByFirstNameAndLastName("Marius", "Filip");
        System.out.println("Var1"+ var1);
        System.out.println(var2);

        final Person byCriteria1 = personRepository.findByCriteria1("Marius");
        final LocalDate byCriteria2 = personRepository.findByCriteria2("Davids");
        System.out.println(byCriteria1);
        System.out.println(byCriteria2);
        Person davids = personRepository.findByCriteria3("Davids");
        int recordsCount = personRepository.getRecordsCount();
        System.out.println(davids);
        System.out.println(recordsCount);
        return personRepository.findAll();
    }

    public Optional<Person> getOneById(long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        List<Address> allAddresses = addressRepository.findAll();
        return optionalPerson;
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
