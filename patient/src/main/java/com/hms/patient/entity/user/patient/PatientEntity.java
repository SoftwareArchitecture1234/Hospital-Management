package com.hms.patient.entity.user.patient;

import com.hms.patient.constant.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name= "Patient")
@Entity
@Getter @Setter
@NoArgsConstructor
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

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @MapsId
//    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
//    private UserEntity user;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
//    private ScheduleEntity scheduleEntity;
}
