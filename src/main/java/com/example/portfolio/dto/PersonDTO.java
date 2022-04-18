package com.example.portfolio.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO implements Serializable {

    private Long id;
    private String first_name;
    private String last_name;
    private String nationality;
    private String profession;
    private String about;
    private Long userphotos_id;
    private String profile_photo;
    private String cover_photo;

}
