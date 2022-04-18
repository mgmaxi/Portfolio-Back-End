package com.example.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "The name of the academic institution is required.")
    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "logo", length = 250)
    private String logo;

    // Relation to Education
    @OneToMany(mappedBy = "school")
    @JsonIgnore
    private List<Education> educations;

    public School() {
    }

    public School(Long id, String name, String logo, List<Education> educations) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.educations = educations;
    }

}
