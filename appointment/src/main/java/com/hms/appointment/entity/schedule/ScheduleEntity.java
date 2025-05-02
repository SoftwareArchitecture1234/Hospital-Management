package com.hms.appointment.entity.schedule;

import com.hms.appointment.constant.ScheduleStatus;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "Schedule")
@Entity
@Setter
public class ScheduleEntity {
    @Id
    private int scheduleId;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name= "date")
    private LocalDate date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;
}
