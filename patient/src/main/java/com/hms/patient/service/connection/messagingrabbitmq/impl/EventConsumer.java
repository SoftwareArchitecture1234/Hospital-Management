package com.hms.patient.service.connection.messagingrabbitmq.impl;

import com.hms.patient.service.connection.messagingrabbitmq.EventPublisherInterface;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


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
        if (serviceName.equals("patient")) {
            System.err.println("Error message received from RabbitMQ: " + body);
        } else {
            eventPublisherInterface.sendErrorMessage(body, serviceName);
        }
    }

    @RabbitListener(queues = "hms.notification-queue")
    public void handleNotificationMessage(Message message) {
        String body = new String(message.getBody());
        String serviceName = message.getMessageProperties().getHeaders().get("serviceName").toString();
        if (serviceName.equals("patient")) {
            System.out.println("Notification message received from RabbitMQ: " + body);
        } else {
            eventPublisherInterface.sendNotification(body, serviceName);
        }
    }
}
