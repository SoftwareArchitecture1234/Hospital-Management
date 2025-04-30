package com.hms.patient.service.scheduleService.impl;

import com.hms.patient.connection.schedule.AppointmentServiceClient;
import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;
import com.hms.patient.service.scheduleService.IScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleServiceImply implements IScheduleService {
    private final AppointmentServiceClient appointmentServiceClient;

    @Override
    public List<AvailableTimeSlotDto> getAvailableTimeSlots(QueryScheduleDto queryScheduleDto) {
        return appointmentServiceClient.getAvailable(queryScheduleDto).getBody();
    }
}
