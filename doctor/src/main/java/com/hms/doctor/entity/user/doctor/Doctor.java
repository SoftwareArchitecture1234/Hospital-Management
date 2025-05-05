package com.hms.doctor.entity.user.doctor;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctors")
public class Doctor {

    @Id
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column
    private String specialties;

    // @OneToOne
    // @MapsId
    // @JoinColumn(name = "user_id", nullable = false)
    // private User user;
}