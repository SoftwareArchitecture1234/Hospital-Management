package com.hms.patient.dtos.accounts;


import com.hms.patient.constant.Gender;
import lombok.Data;

@Data
public class PatientInfoDto {
    private String name;
    private String email;
    private String phone;
    private String location;
    private int age;
    private float weight;
    private float height;
    private String gender;

    public PatientInfoDto(String name,
                          String email,
                          String phone,
                          String location,
                          int age,
                          float weight,
                          float height,
                          Gender gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender.toString();
    }
}

