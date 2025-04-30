package com.hms.patient.dtos.schedule;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AvailableTimeSlotDto {
    private int doctorId;
    private String shiftType;
    private String dateOfWeek;
    private String room;
    private String startTime;
    private String endTime;
    private String dateExam;
    private String note;
}
