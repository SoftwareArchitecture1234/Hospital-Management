package com.hms.auth.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    @NotEmpty
    private String name;

    @NotEmpty
    private String username;

    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    private String phone;

    private String location; 

    private List<String> roles;

    private int age;

    private float weight;

    private float height;

    private String gender;

    private String specialties;
}
