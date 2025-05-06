package com.hms.patient.service.connection.messagingrabbitmq.impl;

import com.hms.patient.service.connection.messagingrabbitmq.EventPublisherInterface;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher implements EventPublisherInterface {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String message, String routingKey, String exchange, String requestSchedule) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message, m -> {
            m.getMessageProperties().setHeader("requestSchedule", requestSchedule);
            return m;
        });
    }

    @Override
    public void sendErrorMessage(String message, String serviceName) {
        rabbitTemplate.convertAndSend(
                "hms.error", "error", message, message1 -> {
                    message1.getMessageProperties().setHeader("serviceName", "patient");
                    return message1;
                }
        );
    }

    @Override
    public void sendNotification(String message, String serviceName, String messageType) {
        rabbitTemplate.convertAndSend(
                "hms.notification", "notification", message, message1 -> {
                    message1.getMessageProperties().setHeader("serviceName", "patient");
                    message1.getMessageProperties().setHeader("messageType", messageType);
                    return message1;
                }
        );
    }
}