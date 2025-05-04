package com.hms.patient.service.scheduleService.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.patient.dtos.schedule.RequestScheduleDto;
import com.hms.patient.service.connection.messagingrabbitmq.EventPublisherInterface;
import com.hms.patient.service.connection.schedule.AppointmentServiceClient;
import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;
import com.hms.patient.service.scheduleService.IScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * ScheduleServiceImply là một lớp dịch vụ thực hiện giao diện IScheduleService.
 * Nó cung cấp các phương thức để lấy danh sách các ca làm việc còn trống từ dịch vụ lịch hẹn.
 */
@Service
public class ScheduleServiceImply implements IScheduleService {
    @Value("${hms.rabbitmq.exchange.name}")
    private String exchange;
    @Value("${hms.rabbitmq.routing.key}")
    private String routingKey;

    private final AppointmentServiceClient appointmentServiceClient;
    private final EventPublisherInterface eventPublisherInterface;
    public ScheduleServiceImply(
            AppointmentServiceClient appointmentServiceClient,
            EventPublisherInterface eventPublisherInterface
    ) {
        this.appointmentServiceClient = appointmentServiceClient;
        this.eventPublisherInterface = eventPublisherInterface;
    }

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

    /**
     * Gửi thông điệp lịch hẹn đến dịch vụ lịch hẹn.
     *
     * @param requestScheduleDto thông tin lịch hẹn
     */
    @Override
    public void sendScheduleMessage(RequestScheduleDto requestScheduleDto) {
        try {
            String message = requestScheduleDto.toString();
            System.out.println("Message to send: " + message);
            eventPublisherInterface.sendMessage(
                    message,
                    routingKey,
                    exchange,
                    ""
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to send schedule message", e);
        }
    }

}
