package com.hms.patient.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TempPatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String phone;
}
