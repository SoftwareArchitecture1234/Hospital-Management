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
            }
            else if(requestScheduleDto.getMessageType().equals("CANCEL")){
                scheduleService.cancelSchedule(requestScheduleDto);
            } 
            else if(requestScheduleDto.getMessageType().equals("RESCHEDULE")){
                scheduleService.reSchedule(requestScheduleDto);
            }
            else {
                throw new RuntimeException("request khong ton tai");
            }
        } catch (Exception e) {
            eventPublisherInterface.sendErrorMessage("Error processing message: " + e.getMessage(), "patient");
        }
    }
}
