package com.hcl.personservice.model;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "city", nullable = false, length = 20)
    private String city;
    @Column(name = "state", nullable = false, length = 20)
    private String state;
    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Address() {
    }

    public Address(long id, String city, String state) {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
