package com.hms.patient.entity.schedule;

import com.hms.patient.constant.ScheduleStatus;
import com.hms.patient.entity.user.doctor.DoctorEntity;
import com.hms.patient.entity.user.patient.PatientEntity;
import jakarta.persistence.*;

@Table(name = "Schedule")
@Entity
public class ScheduleEntity {
    @Id
    private int scheduleId;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
    private DoctorEntity doctorEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
    private PatientEntity patientEntity;
}
