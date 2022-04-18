package com.example.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "technology")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Technology name is required.")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @NotEmpty(message = "Technology category is required.")
    @Column(name = "category", length = 60, nullable = false)
    private String category;

    @Column(name = "logo", length = 250)
    private String logo;

    @Column(name = "url", length = 250)
    private String url;

    //Relation to Person
    @ManyToMany(mappedBy = "technologies")
    @JsonBackReference
    private List<Person> persons;

    public Technology() {
    }

    public Technology(Long id, String name, String category, String logo, String url, List<Person> persons) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.logo = logo;
        this.url = url;
        this.persons = persons;
    }

}
