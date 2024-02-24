package com.hcl.personservice.exception;

public class PersonNotFoundException extends RuntimeException{
    private long id;

    public PersonNotFoundException() {}

    public PersonNotFoundException(long id){
        super(String.format("Person with id %d not found", id));
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
