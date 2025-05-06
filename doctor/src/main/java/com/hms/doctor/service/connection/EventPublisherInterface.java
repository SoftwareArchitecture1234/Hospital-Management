package com.hms.doctor.service.connection;

/**
 * Interface này được sử dụng để gửi các thông điệp đến RabbitMQ.
 * Nó định nghĩa các phương thức để gửi thông điệp, thông điệp lỗi và thông báo.
 */
public interface EventPublisherInterface {
    /**
     * Gửi một thông điệp đến RabbitMQ với các tham số đã chỉ định.
     *
     * @param message        Thông điệp cần gửi.
     * @param routingKey     Khóa định tuyến để xác định đích của thông điệp.
     * @param exchange       Tên của exchange mà thông điệp sẽ được gửi đến.
     * @param requestSchedule Thời gian lên lịch yêu cầu (nếu có).
     */
    void sendMessage(String message, String routingKey, String exchange, String requestSchedule);

    /**
     * Gửi một thông điệp lỗi đến RabbitMQ với các tham số đã chỉ định.
     * @param message      Thông điệp lỗi cần gửi.
     * @param serviceName  Tên dịch vụ mà thông điệp lỗi thuộc về.
     */
    void sendErrorMessage(String message, String serviceName);

    /**
     * Gửi một thông báo đến RabbitMQ với các tham số đã chỉ định.
     * @param message     Thông báo cần gửi.
     * @param serviceName Tên dịch vụ mà thông báo thuộc về.
     * @param messageType Loại thông báo (nếu có).
     */
    void sendNotification(String message, String serviceName, String messageType);
}
