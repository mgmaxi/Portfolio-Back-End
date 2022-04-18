package com.example.portfolio.entity;

import com.example.portfolio.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name is required.")
    @Size(min = 3, message = "First name must have at least 3 characters.")
    @Column(name = "first_name", length = 60, nullable = false)
    private String first_name;
    
    @NotEmpty(message = "Last name is required.")
    @Size(min = 3, message = "Last name must have at least 3 characters.")
    @Column(name = "last_name", length = 60, nullable = false)
    private String last_name;

    @Column(name = "nationality", length = 60)
    private String nationality;

    @Column(name = "profession", length = 60)
    private String profession;

    @Column(name = "about", length = 250)
    private String about;

    // Relation to User
    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private User user;

    //Relation to Language
    @ManyToMany
    @JoinTable(
            name = "person_language",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages;

    //Relation to Technology
    @ManyToMany
    @JoinTable(
            name = "person_technology",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private List<Technology> technologies;

    //Relation to Project
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    //Relation to Education
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;

    //Relation to Experience
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;
    
    // Relation to SocialNetwork
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private SocialNetwork  social_networks;

    public Person() {
    }

    public Person(Long id, String first_name, String last_name, String nationality, String profession, String about, User user, List<Language> languages, List<Technology> Technologies, List<Project> projects, List<Education> educations, List<Experience> experiences, SocialNetwork  social_networks) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.nationality = nationality;
        this.profession = profession;
        this.about = about;
        this.user = user;
        this.languages = languages;
        this.technologies = Technologies;
        this.projects = projects;
        this.educations = educations;
        this.experiences = experiences;
        this.social_networks = social_networks;
    }

    public void personKnowsTechnology(Technology technology) {
        technologies.add(technology);
    }

    public void personKnowsLanguage(Language language) {
        languages.add(language);
    }

}
