package com.example.portfolio.entity;

import com.example.portfolio.entity.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "social_network")
public class SocialNetwork {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "linkedin", length = 250)
    private String linkedin;
    
    @Column(name = "github", length = 250)
    private String github;
    
    @Column(name = "email", length = 250)
    private String email;
    
    // Relation to Person
    @OneToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    public SocialNetwork() {
    }

    public SocialNetwork(Long id, String linkedin, String github, String email) {
        this.id = id;
        this.linkedin = linkedin;
        this.github = github;
        this.email = email;
    }

}
