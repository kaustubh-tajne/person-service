package com.hcl.personservice.service;

import com.hcl.personservice.dao.service.PersonDaoService;
import com.hcl.personservice.dto.AddressDto;
import com.hcl.personservice.dto.PersonDto;
import com.hcl.personservice.dto.ProjectDto;
import com.hcl.personservice.exception.PersonNotFoundException;
import com.hcl.personservice.model.Address;
import com.hcl.personservice.model.Person;
import com.hcl.personservice.model.Project;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope(value = "singleton")
public class PersonService {
    private final static String SPACE = " ";

    private final PersonDaoService personDaoService;

    @Autowired
    public PersonService(PersonDaoService personDaoService) {
        this.personDaoService = personDaoService;
    }
//    private ModelMapper modelMapper = new ModelMapper();

    public List<PersonDto> getAll() {
        final List<Person> persons = personDaoService.getAll();
        return toDto(persons);
    }

    public PersonDto getOneById(long id) {
        System.out.println("#### 1 Get one ");
        final Optional<Person> optionalPerson = personDaoService.getOneById(id);
        System.out.println("#### 2 Get one ");
        if (optionalPerson.isEmpty()) {
            System.out.println("#### 3 Get one ");
//            throw new EntityNotFoundException("Person with #id"+id+" not found");
            throw new PersonNotFoundException(id);
//            return null;
        }
        System.out.println("#### 4 Get one ");
        return toDto(optionalPerson.get());
    }

    public PersonDto create(PersonDto personDto) {
        System.out.println("#### Create 1");
        final Person person = toEntity(personDto);
        final Person savedPerson = personDaoService.create(person);
        return toDto(savedPerson);
    }

    public PersonDto update(PersonDto personDto) {
        final Person person = toEntity(personDto);
        final Person updatedPerson = personDaoService.update(person);
        return toDto(updatedPerson);
    }

    public boolean delete(long id) {
       return personDaoService.delete(id);
    }

    // Utility methods ---------------------------------------------------
    private List<PersonDto> toDto(List<Person> persons) {
        return persons.stream()
                .map(person -> toDto(person))
                .collect(Collectors.toList());
    }

    private PersonDto toDto(Person person) {
//        final TypeMap<Person, PersonDto> typeMap = modelMapper.createTypeMap(Person.class, PersonDto.class);
//        typeMap.addMapping(Person::getFirstName, PersonDto::setFullName);
//        final PersonDto map = typeMap.map(person);

        PersonDto result = new PersonDto();
        result.setId(person.getId());
        result.setFirstName(person.getFirstName());
        result.setLastName(person.getLastName());
        result.setFullName(fullName(person.getFirstName(), person.getLastName()));
        result.setJoiningDate(person.getJoiningDate());
        result.setNumberOfYearsOfExperience(findExperience(person.getJoiningDate()));

        final ProjectDto projectDto = toDto(person.getProject());
        result.setProjectDto(projectDto);

        final Set<AddressDto> addressDtos = toDto(person.getAddresses());
        result.setAddressDtos(addressDtos);

        return result;
    }

    private Set<AddressDto> toDto(Set<Address> addresses) {

        return addresses
                .stream()
                .map(address -> toDto(address))
                .collect(Collectors.toSet());
    }

    private AddressDto toDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        return addressDto;
    }

    private ProjectDto toDto(Project project) {
        final ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setTechnology(project.getTechnology());
        return projectDto;
    }

    private String findExperience(LocalDate joiningDate) {
        final long experience = ChronoUnit.YEARS.between(joiningDate, LocalDate.now());
        return String.valueOf(experience);
    }

    private String fullName(final String firstName, final String lastName) {
        return firstName + SPACE + lastName;
    }

    private Person toEntity(PersonDto personDto) {
        final Person person = new Person();
        person.setId(personDto.getId());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setJoiningDate(personDto.getJoiningDate());

        // Project Dto
        final ProjectDto projectDto = personDto.getProjectDto();
        final Project project = toEntity(projectDto);

        person.setProject(project);
        project.setPerson(person);

        // Address Dto
        Set<AddressDto> addressDtos = personDto.getAddressDtos();
        Set<Address> addresses = toEntity(addressDtos);

        person.setAddresses(addresses);

        // As it is bidirectional rel we have to set Person entity for address also
        addresses.forEach(address -> address.setPerson(person));

        // Normal For Loop
//        for (final Address address: addresses) {
//            address.setPerson(person);
//        }

        return person;
    }

    private Set<Address> toEntity(Set<AddressDto> addressDtos) {
        return addressDtos
                            .stream()
                .map(addressDto -> toEntity(addressDto))
                .collect(Collectors.toSet());
    }

    private Address toEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        return address;
    }

    private Project toEntity(ProjectDto projectDto) {
        final Project project = new Project();
        project.setId(projectDto.getId());
        project.setTitle(projectDto.getTitle());
        project.setTechnology(projectDto.getTechnology());

        return project;
    }

}
