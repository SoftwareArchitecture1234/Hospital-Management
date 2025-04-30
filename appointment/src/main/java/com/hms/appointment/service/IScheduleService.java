package com.hms.appointment.service;

import com.hms.appointment.dtos.schedule.AvailableTimeSlotDto;
import com.hms.appointment.dtos.schedule.QueryScheduleDto;

import java.util.List;

public interface IScheduleService {
    List<AvailableTimeSlotDto> getAvailableSlots(QueryScheduleDto queryScheduleDto);
}
