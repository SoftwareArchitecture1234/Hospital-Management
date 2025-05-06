package com.hms.doctor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQClientConfig là class cấu hình cho RabbitMQ client.
 * Nó định nghĩa các bean cho TopicExchange, Queue và Binding.
 * Các bean này sẽ được sử dụng để gửi và nhận thông điệp giữa các dịch vụ trong hệ thống.
 */
@Configuration
public class RabbitMQClientConfig {

    /**
     * Tên của TopicExchange cho dịch vụ Appointment.
     */
    @Bean
    public TopicExchange errorTopicExchange() {
        return new TopicExchange("hms.error", true, false);
    }

    /**
     * Thiết lập hàng đợi cho các thông điệp lỗi.
     */
    @Bean
    public Queue errorQueue() {
        return new Queue("hms.error-queue", true);
    }

    /**
     * Thiết lập Binding giữa hàng đợi và TopicExchange cho các thông điệp lỗi.
     */
    @Bean
    public Binding errorBinding(Queue errorQueue, TopicExchange errorTopicExchange) {
        return BindingBuilder
                .bind(errorQueue)
                .to(errorTopicExchange)
                .with("error.#");
    }

    /**
     * Tên của TopicExchange cho dịch vụ Notification.
     */
    @Bean
    public TopicExchange notificationTopicExchange() {
        return new TopicExchange("hms.notification", true, false);
    }

    /**
     * Thiết lập hàng đợi cho các thông điệp Notification.
     */
    @Bean
    public Queue notificationQueue() {
        return new Queue("hms.notification-queue", true);
    }

    /**
     * Thiết lập Binding giữa hàng đợi và TopicExchange cho các thông điệp Notification.
     */
    @Bean
    public Binding notificationBinding(Queue notificationQueue, TopicExchange notificationTopicExchange) {
        return BindingBuilder
                .bind(notificationQueue)
                .to(notificationTopicExchange)
                .with("notification.#");
    }

    /**
     * Tên của TopicExchange cho dịch vụ Doctor.
     */
    @Bean
    public TopicExchange doctorTopicExchange() {
        return new TopicExchange("hms.doctor", true, false);
    }

    /**
     * Thiết lập hàng đợi cho các thông điệp Doctor.
     */
    @Bean
    public Queue doctorQueue() {
        return new Queue("hms.doctor-queue", true);
    }

    /**
     * Thiết lập Binding giữa hàng đợi và TopicExchange cho các thông điệp Doctor.
     */
    @Bean
    public Binding doctorBinding(Queue doctorQueue, TopicExchange doctorTopicExchange) {
        return BindingBuilder
                .bind(doctorQueue)
                .to(doctorTopicExchange)
                .with("hms.doctor.roting.key");
    }
}
