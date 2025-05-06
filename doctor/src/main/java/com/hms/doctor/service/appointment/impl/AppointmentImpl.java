package com.hms.doctor.service.appointment.impl;

import com.hms.doctor.dto.schedule.RequestScheduleDto;
import com.hms.doctor.service.appointment.IAppointmentService;
import com.hms.doctor.service.connection.EventPublisherInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentImpl implements IAppointmentService {
    @Autowired
    private EventPublisherInterface eventPublisherInterface;
    /**
     * Xác nhận đặt lịch hẹn thành công
     *
     * @Param appointmentDto data về lịch hẹn
     */
    @Override
    public void confirmAppointment(RequestScheduleDto appointment) {
        appointment.setMessageType("CONFIRM");
        eventPublisherInterface.sendMessage(
                appointment.toString(),
                "hms.doctor.roting.key",
                "hms.doctor",
                ""
        );
    }


    /**
     * Hủy lịch hẹn
     *
     * @Param appointmentDto data về lịch hẹn
     */
    @Override
    public void cancelAppointment(RequestScheduleDto appointment) {
        appointment.setMessageType("CANCEL");
        eventPublisherInterface.sendMessage(
                appointment.toString(),
                "hms.doctor.roting.key",
                "hms.doctor",
                ""
        );
    }
}
