package com.hms.doctor.service.appointment;

import com.hms.doctor.dto.schedule.RequestScheduleDto;

public interface IAppointmentService {
    /**
     * Xác nhận đặt lịch hẹn thành công
     * @Param appointmentId ID của lịch hẹn
     */
    void confirmAppointment(RequestScheduleDto appointment);

    /**
     * Hủy lịch hẹn
     * @Param appointmentId ID của lịch hẹn
     */
    void cancelAppointment(RequestScheduleDto appointment);
}
