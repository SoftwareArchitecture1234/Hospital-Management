package com.hms.doctor.entity.workload;

import java.time.LocalDateTime;

import com.hms.doctor.constant.WorkloadStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "Workload")
@Entity
@Getter
@Setter
public class WorkloadEntity {

  @Id
  @Column(name = "workload_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int workloadId;

  @Column(name = "doctor_id")
  private int doctorId;

  @Column(name = "date_of_week")
  private String dateOfWeek;

  @Column(name = "type_of_work")
  private String typeOfWork;

  @Column(name = "shift")
  private String shift;

  @Column(name = "time")
  private LocalDateTime time;

  @Column(name = "room")
  private String room;

  @Column(name = "note", length = 1000)
  private String note;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private WorkloadStatus status;

  // Getters và Setters (hoặc dùng Lombok nếu đã thêm Lombok)
}
