package com.hms.appointment.service.impl;

import com.hms.appointment.constant.ShiftType;
import com.hms.appointment.constant.TypeOfWork;
import com.hms.appointment.dtos.schedule.AvailableTimeSlotDto;
import com.hms.appointment.dtos.schedule.QueryScheduleDto;
import com.hms.appointment.entity.schedule.ScheduleEntity;
import com.hms.appointment.entity.workload.WorkloadEntity;
import com.hms.appointment.mapper.EntityMapper;
import com.hms.appointment.repository.ScheduleRepository;
import com.hms.appointment.repository.WorkloadRepository;
import com.hms.appointment.service.IScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements IScheduleService {
    private final WorkloadRepository workloadRepository;
    private final ScheduleRepository scheduleRepository;
    @Override
    public List<AvailableTimeSlotDto> getAvailableSlots(QueryScheduleDto queryScheduleDto) {
        String dateExam = queryScheduleDto.getDateExam();
        String shift = queryScheduleDto.getShift();
        String typeExam = queryScheduleDto.getTypeExam();
        String startTime = queryScheduleDto.getStartTime();
        String endTime = queryScheduleDto.getEndTime();

        List<WorkloadEntity> listWorkload = workloadRepository.findAvailableWorkload(
                handleDateFromDateExam(dateExam),
                LocalTime.parse(startTime),
                LocalTime.parse(endTime),
                ShiftType.valueOf(shift).getShiftName(),
                TypeOfWork.valueOf(typeExam).getTypeOfWork()
        );

        if (listWorkload.isEmpty()) {
            return null;
        }
        List<AvailableTimeSlotDto> availTimeSlot = getAvailableTimeSlotDto(
                listWorkload,
                handleDateFromDateExam(dateExam),
                LocalTime.parse(startTime),
                LocalTime.parse(endTime)
        );
        return availTimeSlot;
    }

    private LocalDate handleDateFromDateExam(String dateExam) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateExam, dateTimeFormatter);
    }

    private List<AvailableTimeSlotDto> getAvailableTimeSlotDto(
            List<WorkloadEntity> listWorkload,
            LocalDate dateExam,
            LocalTime startTime,
            LocalTime endTime
    ) {
        return listWorkload.stream()
                .filter(workloadEntity -> {
                    int doctorId = workloadEntity.getDoctorId();
                    ScheduleEntity scheduleEntity = scheduleRepository.findScheduleByDoctorIdAndDate(
                            dateExam, startTime, endTime, doctorId
                    );
                    return scheduleEntity == null;
                })
                .map(EntityMapper::workloadEntityToDto)
                .collect(Collectors.toList());
    }
}
