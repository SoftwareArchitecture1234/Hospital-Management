package com.hms.patient.service.scheduleService;

import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;

import java.util.List;

public interface IScheduleService {
    /**
     * Lấy danh sách các ca làm việc còn trống dựa trên thông tin truy vấn.
     *
     * @param queryScheduleDto thông tin truy vấn lịch hẹn
     * @return danh sách các ca làm việc còn trống
     */
    public List<AvailableTimeSlotDto> getAvailableTimeSlots(
            QueryScheduleDto queryScheduleDto
    );
}
