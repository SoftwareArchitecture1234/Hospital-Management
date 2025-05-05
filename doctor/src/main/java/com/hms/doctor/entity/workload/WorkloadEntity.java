package com.hms.doctor.entity.workload;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workload")
public class WorkloadEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "workload_id")
  private Long workloadId;

  @Column(name = "doctor_id", nullable = false)
  private Integer doctorId;

  @Column(name = "shift")
  private String shift;

  @Column(name = "day_of_week")
  private String dayOfWeek;

  @Column(name = "type_of_work")
  private String typeOfWork;

  @Column(name = "room")
  private String room;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "end_time")
  private LocalTime endTime;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "note")
  private String note;
}
