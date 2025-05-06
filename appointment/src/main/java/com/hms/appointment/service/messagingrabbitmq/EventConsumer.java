package com.hms.appointment.service.messagingrabbitmq;
import com.hms.appointment.dtos.schedule.RequestScheduleDto;
import com.hms.appointment.service.EventPublisherInterface;
import com.hms.appointment.service.IScheduleService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    private final EventPublisherInterface eventPublisherInterface;
    private final IScheduleService scheduleService;
    public EventConsumer(EventPublisherInterface eventPublisherInterface, IScheduleService scheduleService) {
        this.eventPublisherInterface = eventPublisherInterface;
        this.scheduleService = scheduleService;
    }

    @RabbitListener(queues = "${hms.rabbitmq.patient.queue.name}")
    public void consumeMessageFromPatient(String message) {
        System.out.println("Message received from RabbitMQ: " + message);
        try {
            RequestScheduleDto requestScheduleDto = RequestScheduleDto.fromString(message);
            if (requestScheduleDto.getMessageType().equals("CREATE")) {
                scheduleService.requestSchedule(requestScheduleDto);
                eventPublisherInterface.sendNotification("Cuộc hẹn mới đã được tạo", "doctor", "new-appointment");
                eventPublisherInterface.sendNotification("Thành công đặt lịch hẹn", "patient", "new-appointment");
            }
            else if(requestScheduleDto.getMessageType().equals("CANCEL")){
                scheduleService.cancelSchedule(requestScheduleDto);
                eventPublisherInterface.sendNotification("Cuộc hẹn đã bị hủy", "doctor", "cancel-appointment");
                eventPublisherInterface.sendNotification("Thành công hủy lịch hẹn", "patient", "cancel-appointment");
            } 
            else if(requestScheduleDto.getMessageType().equals("RESCHEDULE")){
                scheduleService.reSchedule(requestScheduleDto);
                eventPublisherInterface.sendNotification("Cuộc hẹn đã được cập nhật", "doctor", "update-appointment");
                eventPublisherInterface.sendNotification("Thành công cập nhật lịch hẹn", "patient", "update-appointment");
            }
            else {
                throw new RuntimeException("Lỗi khi xử lý thông điệp: " + message);
            }
        } catch (Exception e) {
            eventPublisherInterface.sendErrorMessage("Lỗi khi xử lý thông điệp: " + message, "patient");
        }
    }

    @RabbitListener(queues = "${hms.rabbitmq.doctor.queue.name}")
    public void consumeMessageFromDoctor(String message) {
        System.out.println("Message received from RabbitMQ: " + message);
        try {
            RequestScheduleDto requestScheduleDto = RequestScheduleDto.fromString(message);
            if (requestScheduleDto.getMessageType().equals("CONFIRM")) {
                scheduleService.confirmSchedule(requestScheduleDto);
                eventPublisherInterface.sendNotification("Cuộc hẹn đã được xác nhận", "patient", "confirm-appointment");
                eventPublisherInterface.sendNotification("Thành công xác nhận lịch hẹn", "doctor", "confirm-appointment");
            } else if (requestScheduleDto.getMessageType().equals("CANCEL")) {
                scheduleService.cancelSchedule(requestScheduleDto);
                eventPublisherInterface.sendNotification("Cuộc hẹn đã bị hủy", "patient", "cancel-appointment");
                eventPublisherInterface.sendNotification("Thành công hủy lịch hẹn", "doctor", "cancel-appointment");
            }
        } catch (Exception e) {
            eventPublisherInterface.sendErrorMessage("Lỗi khi xử lý thông điệp: " + message, "doctor");
        }
    }
}
