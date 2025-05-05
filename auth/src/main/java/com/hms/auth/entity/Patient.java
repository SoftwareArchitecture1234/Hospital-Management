package com.hms.auth.entity;

import java.sql.Struct;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "patient_id")
    private Integer patientId;

    @Column
    private int age;

    @Column
    private float weight;

    @Column
    private float height;

    @Column
    private String gender;

    // @OneToOne
    // @MapsId
    // @JoinColumn(name = "user_id", nullable = false)
    // private User user;
}
