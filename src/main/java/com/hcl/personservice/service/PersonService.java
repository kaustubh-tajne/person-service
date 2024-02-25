package com.hcl.personservice.service;

import com.hcl.personservice.dao.service.PersonDaoService;
import com.hcl.personservice.dto.PersonDto;
import com.hcl.personservice.dto.ProjectDto;
import com.hcl.personservice.exception.PersonNotFoundException;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope(value = "singleton")
public class PersonService {
    private final static String SPACE = " ";

    @Autowired
    private PersonDaoService personDaoService;
//    private ModelMapper modelMapper = new ModelMapper();

    public List<PersonDto> getAll() {
        final List<Person> persons = personDaoService.getAll();
        return toDto(persons);
    }

    public PersonDto getOneById(long id) {
        final Optional<Person> optionalPerson = personDaoService.getOneById(id);
        if (optionalPerson.isEmpty()) {
//            throw new EntityNotFoundException("Person with #id"+id+" not found");
            throw new PersonNotFoundException(id);
        }
        return toDto(optionalPerson.get());
    }

    public PersonDto create(PersonDto personDto) {
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

        return result;
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

        final ProjectDto projectDto = personDto.getProjectDto();
        final Project project = toEntity(projectDto);

        person.setProject(project);

        return person;
    }

    private Project toEntity(ProjectDto projectDto) {
        final Project project = new Project();
        project.setId(projectDto.getId());
        project.setTitle(projectDto.getTitle());
        project.setTechnology(projectDto.getTechnology());

        return project;
    }

}
