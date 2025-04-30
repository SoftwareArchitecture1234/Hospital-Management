package com.hms.patient.service.scheduleService;

import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;

import java.util.List;

public interface IScheduleService {
    public List<AvailableTimeSlotDto> getAvailableTimeSlots(
            QueryScheduleDto queryScheduleDto
    );
}
