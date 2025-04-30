package com.hms.appointment.entity.workload;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Workload")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadEntity {
    @Id
    @Column(name = "workload_id")
    private int workloadId;

    @Column(name = "doctor_id", nullable = false)
    private int doctorId;

    @Column(name = "shift", nullable = false)
    private String shiftType;

    @Column(name = "day_of_week", nullable = false)
    private String dateOfWeek;

    @Column(name = "type_of_work", nullable = false)
    private String typeOfWork;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "date", nullable = false)
    private LocalDate dateTime;

    @Column(name = "note", nullable = false)
    private String note;
}
