package com.hms.doctor.service.connection.impl;


import com.hms.doctor.service.connection.EventPublisherInterface;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class EventConsumer {
    private final EventPublisherInterface eventPublisherInterface;

    public EventConsumer(EventPublisherInterface eventPublisherInterface) {
        this.eventPublisherInterface = eventPublisherInterface;
    }


    @RabbitListener(queues = "hms.error-queue")
    public void handleErrorMessage(Message message) {
        String body = new String(message.getBody());
        String serviceName = message.getMessageProperties().getHeaders().get("serviceName").toString();
        if (serviceName.equals("doctor")) {
            System.err.println("Error message received from RabbitMQ: " + body);
        } else {
            eventPublisherInterface.sendErrorMessage(body, serviceName);
        }
    }

    @RabbitListener(queues = "hms.notification-queue")
    public void handleNotificationMessage(Message message) {
        String body = new String(message.getBody());
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        String serviceName = headers.get("serviceName").toString();
        String messageType = headers.get("messageType").toString();

        if (serviceName.equals("doctor")) {
            System.out.println(message);
        } else {
            eventPublisherInterface.sendNotification(body, serviceName, messageType);
        }
    }
}
