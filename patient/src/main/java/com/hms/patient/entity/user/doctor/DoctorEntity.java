package com.hms.patient.entity.user.doctor;

import com.hms.patient.entity.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "Doctor")
public class DoctorEntity {
    @Id
    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "specialties")
    private String specialties;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "user_id")
    private UserEntity user;
}
