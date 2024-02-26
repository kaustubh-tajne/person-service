package com.hcl.personservice.dto;

import com.hcl.personservice.model.Address;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.message.Message;

import java.time.LocalDate;
import java.util.Set;

public class PersonDto {
    private long id;
    @NotEmpty
    @Size(min = 6, max = 10)
    private String firstName;
    @NotEmpty
    @Size(min = 6, max = 10, message = "value should be between the min and max")
    private String lastName;
    @Null(message = "FullName should be null")
    private String fullName;
    @NotNull
    private LocalDate joiningDate;
    @Null(message = "experience should be null")
    private String numberOfYearsOfExperience;
    private ProjectDto projectDto;
    private Set<AddressDto> addressDtos;

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

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

    public Set<AddressDto> getAddressDtos() {
        return addressDtos;
    }

    public void setAddressDtos(Set<AddressDto> addressDtos) {
        this.addressDtos = addressDtos;
    }
}
