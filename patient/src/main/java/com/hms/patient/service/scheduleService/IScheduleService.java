package com.hms.patient.service.scheduleService;

import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;
import com.hms.patient.dtos.schedule.RequestScheduleDto;

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

    /**
     * Gửi thông điệp lịch hẹn đến dịch vụ lịch hẹn.
     *
     * @param requestScheduleDto thông tin lịch hẹn
     */
    public void sendScheduleMessage(
            RequestScheduleDto requestScheduleDto
    );
}
