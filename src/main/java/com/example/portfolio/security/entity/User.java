package com.example.portfolio.security.entity;

import com.example.portfolio.entity.UserPhotos;
import com.example.portfolio.entity.Person;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username is required.")
    @Size(min = 3, message = "Username must have at least 3 characters.")
    @Column(name = "username", length = 60, nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "Email is required.")
    @Email
    @Column(name = "email", length = 250, nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Password is required.")
    @Size(min = 6, message = "Password must have at least 6 characters.")
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // Relation to Person
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    // Relation to UserPhotos
    @OneToOne
    @JoinColumn(name = "user_photos_id")
    private UserPhotos user_photos;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
