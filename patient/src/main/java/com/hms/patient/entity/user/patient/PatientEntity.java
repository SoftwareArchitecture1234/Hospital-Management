package com.hms.patient.entity.user.patient;

import com.hms.patient.entity.schedule.ScheduleEntity;
import com.hms.patient.entity.user.UserEntity;
import jakarta.persistence.*;

@Table(name= "Patient")
@Entity
public class PatientEntity {
    @Id
    @Column(name = "patient_id")
    private int patientId;

    @Column(name= "age")
    private int age;

    @Column(name= "weight")
    private float weight;

    @Column(name = "height")
    private float height;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToOne
    @MapsId
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
    private ScheduleEntity scheduleEntity;
}
