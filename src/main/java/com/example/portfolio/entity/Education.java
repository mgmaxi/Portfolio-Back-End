package com.example.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Education name is required.")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @NotEmpty(message = "Description is required.")
    @Size(min = 10, message = "Description must have at least 10 characters.")
    @Size(max = 250, message = "Description must have a maximum of 250 characters.")
    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @NotNull(message = "Start date is required.")
    @Column(name = "start_date", length = 60, nullable = false)
    private LocalDate start_date;

    @NotNull(message = "End date is required.")
    @Column(name = "end_date", length = 60, nullable = false)
    private LocalDate end_date;

    // Relation to Person
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    @JsonBackReference
    private Person person;

    // Relation to School
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Education() {
    }

    public Education(Long id, String name, String description, LocalDate start_date, LocalDate end_date, Person person, School school) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.person = person;
        this.school = school;
    }

}
