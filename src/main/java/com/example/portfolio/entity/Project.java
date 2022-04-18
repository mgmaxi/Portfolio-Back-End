package com.example.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Project name is required.")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @NotEmpty(message = "Description is required.")
    @Size(min = 10, message = "Description must have at least 10 characters.")
    @Size(max = 250, message = "Description must have a maximum of 250 characters.")
    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "repository", length = 250)
    private String repository;

    @Column(name = "deploy", length = 250)
    private String deploy;

    @Column(name = "end_date", length = 250)
    private LocalDate end_date;

    @Column(name = "logo", length = 250)
    private String logo;

    // Relation to Person
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;

    public Project() {
    }

    public Project(Long id, String name, String description, String repository, String deploy, LocalDate end_date, String logo, Person person) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.repository = repository;
        this.deploy = deploy;
        this.end_date = end_date;
        this.logo = logo;
        this.person = person;
    }

}
