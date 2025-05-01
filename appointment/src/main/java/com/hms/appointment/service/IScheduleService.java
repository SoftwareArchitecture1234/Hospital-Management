package com.hms.appointment.service;

import com.hms.appointment.dtos.schedule.AvailableTimeSlotDto;
import com.hms.appointment.dtos.schedule.QueryScheduleDto;

import java.util.List;

/**
 * IScheduleService là một interface định nghĩa các phương thức để xử lý các yêu cầu liên quan đến lịch hẹn.
 * Nó cung cấp các phương thức để lấy danh sách lịch hẹn dựa trên thông tin truy vấn.
 */
public interface IScheduleService {
    /**
     * Lấy danh sách các ca làm việc còn trống dựa trên thông tin truy vấn.
     *
     * @param queryScheduleDto thông tin truy vấn lịch hẹn
     * @return danh sách các ca làm việc còn trống
     */
    List<AvailableTimeSlotDto> getAvailableSlots(QueryScheduleDto queryScheduleDto);
}
