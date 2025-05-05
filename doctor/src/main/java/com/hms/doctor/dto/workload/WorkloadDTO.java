package com.hms.doctor.dto.workload;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WorkloadDTO {
    private Long workloadId;
    private Integer doctorId;
    private String shift;
    private String dayOfWeek;
    private String typeOfWork;
    private String room;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String note;
}
