package com.example.portfolio.entity;

import com.example.portfolio.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "user_photos")
public class UserPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "profile_photo", length = 250)
    private String profile_photo;
    @Column(name = "cover_photo", length = 250)
    private String cover_photo;

    // Relation to User
    @OneToOne(mappedBy = "user_photos")
    @JsonIgnore
    private User user;

    public UserPhotos() {
    }

    public UserPhotos(Long id, String profile_photo, String cover_photo, User user) {
        this.id = id;
        this.profile_photo = profile_photo;
        this.cover_photo = cover_photo;
        this.user = user;
    }

}
