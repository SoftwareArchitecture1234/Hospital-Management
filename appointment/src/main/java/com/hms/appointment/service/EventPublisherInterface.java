package com.hms.appointment.service;

/**
 * EventPublisherInterface là một interface định nghĩa các phương thức để gửi thông điệp sự kiện.
 * Nó cung cấp các phương thức để gửi thông điệp lỗi và thông báo đến các dịch vụ khác.
 */
public interface EventPublisherInterface {
    /**
     * Gửi thông điệp lỗi đến dịch vụ khác.
     *
     * @param message      thông điệp lỗi
     * @param serviceName  tên dịch vụ nhận thông điệp
     */
    void sendErrorMessage(String message, String serviceName);

    /**
     * Gửi thông báo đến dịch vụ khác.
     *
     * @param message      thông điệp thông báo
     * @param serviceName  tên dịch vụ nhận thông báo
     */
    void sendNotification(String message, String serviceName);
}
