package com.hms.patient.service.scheduleService.impl;

import com.hms.patient.connection.schedule.AppointmentServiceClient;
import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;
import com.hms.patient.service.scheduleService.IScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * ScheduleServiceImply là một lớp dịch vụ thực hiện giao diện IScheduleService.
 * Nó cung cấp các phương thức để lấy danh sách các ca làm việc còn trống từ dịch vụ lịch hẹn.
 */
@Service
@AllArgsConstructor
public class ScheduleServiceImply implements IScheduleService {
    private final AppointmentServiceClient appointmentServiceClient;

    /**
     * Lấy danh sách các ca làm việc còn trống dựa trên thông tin truy vấn.
     *
     * @param queryScheduleDto thông tin truy vấn lịch hẹn
     * @return danh sách các ca làm việc còn trống
     */
    @Override
    public List<AvailableTimeSlotDto> getAvailableTimeSlots(QueryScheduleDto queryScheduleDto) {
        return appointmentServiceClient.getAvailable(queryScheduleDto).getBody();
    }
}
