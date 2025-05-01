package com.hms.appointment.mapper;

import com.hms.appointment.dtos.schedule.AvailableTimeSlotDto;
import com.hms.appointment.entity.workload.WorkloadEntity;

public class EntityMapper {
    public static AvailableTimeSlotDto workloadEntityToDto(
            WorkloadEntity workloadEntity
    ) {
        AvailableTimeSlotDto dto = new AvailableTimeSlotDto();
        dto.setDoctorId(workloadEntity.getDoctorId());
        dto.setShiftType(workloadEntity.getShiftType());
        dto.setDateOfWeek(workloadEntity.getDateOfWeek());
        dto.setRoom(workloadEntity.getRoom());
        dto.setStartTime(workloadEntity.getStartTime().toString());
        dto.setEndTime(workloadEntity.getEndTime().toString());
        dto.setDateExam(workloadEntity.getDateTime().toString());
        dto.setNote(workloadEntity.getNote());
        return dto;
    }
}
