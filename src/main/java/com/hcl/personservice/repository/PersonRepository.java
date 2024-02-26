package com.hcl.personservice.repository;

import com.hcl.personservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String firstName);

    List<Person> findByLastName(String lastName);

    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findByFirstNameOrLastName(String firstName, String lastName);

    default List<Person> findByCriteria(String firstName) {
        List<Person> personList = findByFirstNameAndLastName("A", "B");
        personList.add(findByFirstName("Name"));
        return personList;
    }

    @Query(value = "SELECT p FROM Person p WHERE p.firstName = :firstName")
    Person findByCriteria1(String firstName);

    @Query(value = "SELECT p.joiningDate FROM Person p WHERE p.firstName = :firstName")
    LocalDate findByCriteria2(String firstName);

    // Using small p as it is sql raw query
    @Query(value = "SELECT * FROM person p WHERE p.first_name = :firstName", nativeQuery = true)
    Person findByCriteria3(String firstName);

    @Query(value = "SELECT count(*) FROM person", nativeQuery = true)
    int getRecordsCount();
}
