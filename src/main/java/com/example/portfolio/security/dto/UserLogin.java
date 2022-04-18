package com.example.portfolio.security.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
