package com.hcl.personservice.model;

import jakarta.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "title", nullable = false, length = 30)
    private String title;
    @Column(name = "technology", nullable = false, length = 30)
    private String technology;
    @OneToOne(mappedBy = "project")
    private Person person;

    public Project() {
    }

    public Project(long id, String title, String technology) {
        this.id = id;
        this.title = title;
        this.technology = technology;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
