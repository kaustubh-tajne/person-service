package com.hcl.personservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class AddressDto {

    private long id;
    @NotEmpty
    @Size(min = 6, max = 10)
    private String city;
    @NotEmpty
    @Size(min = 6, max = 10, message = "value should be between the min and max")
    private String state;

    public AddressDto() {
    }

    public AddressDto(long id, String city, String state) {
        this.id = id;
        this.city = city;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
