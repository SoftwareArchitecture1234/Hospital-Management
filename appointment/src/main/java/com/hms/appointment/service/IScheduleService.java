package com.hms.appointment.service;

import com.hms.appointment.dtos.schedule.AvailableTimeSlotDto;
import com.hms.appointment.dtos.schedule.QueryScheduleDto;
import com.hms.appointment.dtos.schedule.RequestScheduleDto;

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

    /**
     * Đặt lịch hẹn dựa trên thông tin yêu cầu.
     *
     * @param requestScheduleDto thông tin yêu cầu đặt lịch
     * @return true nếu đặt lịch thành công, false nếu không thành công
     */
    boolean requestSchedule(RequestScheduleDto requestScheduleDto);

    /**
     * Cập nhật lịch hẹn dựa trên thông tin yêu cầu.
     *
     * @param requestScheduleDto thông tin yêu cầu cập nhật lịch
     * @return true nếu cập nhật lịch thành công, false nếu không thành công
     */
    boolean reSchedule(RequestScheduleDto requestScheduleDto);

    /**
     * Hủy lịch hẹn dựa trên thông tin yêu cầu.
     *
     * @param requestScheduleDto thông tin yêu cầu hủy lịch
     * @return true nếu hủy lịch thành công, false nếu không thành công
     */
    boolean cancelSchedule(RequestScheduleDto requestScheduleDto);

    /**
     * Xác nhận lịch hẹn dựa trên thông tin yêu cầu.
     *
     * @param requestScheduleDto thông tin yêu cầu xác nhận lịch
     * @return true nếu xác nhận lịch thành công, false nếu không thành công
     */
    boolean confirmSchedule(RequestScheduleDto requestScheduleDto);
}
