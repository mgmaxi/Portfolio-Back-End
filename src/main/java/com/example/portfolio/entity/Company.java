package com.example.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Company name is required.")
    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "logo", length = 250)
    private String logo;

    // Relation to Experience
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Experience> experiences;

    public Company() {
    }

    public Company(Long id, String name, String logo, List<Experience> experiences) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.experiences = experiences;
    }

}
