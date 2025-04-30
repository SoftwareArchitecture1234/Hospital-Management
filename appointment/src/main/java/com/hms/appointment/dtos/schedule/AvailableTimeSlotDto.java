package com.hms.appointment.dtos.schedule;

import com.hms.appointment.constant.DateOfWeek;
import com.hms.appointment.constant.ShiftType;
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
