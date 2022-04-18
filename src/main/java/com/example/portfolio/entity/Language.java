package com.example.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Language is required.")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    //Relation to Person
    @ManyToMany(mappedBy = "languages")
    @JsonBackReference
    private List<Person> persons;

    public Language() {
    }

    public Language(Long id, String name, List<Person> persons) {
        this.id = id;
        this.name = name;
        this.persons = persons;
    }

}
