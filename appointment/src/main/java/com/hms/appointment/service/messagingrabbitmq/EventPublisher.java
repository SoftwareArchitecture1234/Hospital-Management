package com.hms.appointment.service.messagingrabbitmq;

import com.hms.appointment.service.EventPublisherInterface;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher implements EventPublisherInterface {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendErrorMessage(String message, String serviceName) {
        rabbitTemplate.convertAndSend(
            "hms.error", "error", message, message1 -> {
                    message1.getMessageProperties().setHeader("serviceName", serviceName);
                    return message1;
            }
        );
        System.out.println("Error message sent to RabbitMQ: " + message);
    }

    public void sendNotification(String message, String serviceName) {
        rabbitTemplate.convertAndSend(
                "hms.notification", "notification", message, message1 -> {
                    message1.getMessageProperties().setHeader("serviceName", serviceName);
                    return message1;
                }
        );
    }
}
