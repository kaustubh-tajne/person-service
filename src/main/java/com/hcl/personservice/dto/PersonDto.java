package com.hcl.personservice.dto;

import java.time.LocalDate;

public class PersonDto {
    private long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate joiningDate;
    private String numberOfYearsOfExperience;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getNumberOfYearsOfExperience() {
        return numberOfYearsOfExperience;
    }

    public void setNumberOfYearsOfExperience(String numberOfYearsOfExperience) {
        this.numberOfYearsOfExperience = numberOfYearsOfExperience;
    }
}
