package com.hms.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column
    private int age;

    @Column
    private float weight;

    @Column
    private float height;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

enum Gender {
    MALE, FEMALE
}