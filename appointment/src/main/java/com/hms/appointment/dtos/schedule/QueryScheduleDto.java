package com.hms.appointment.dtos.schedule;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class QueryScheduleDto {
    private String dateExam; // 26/11/2025
    private String startTime; // 08:00
    private String endTime; // 17:00
    private String shift;
    private String typeExam;
}
