package com.hms.patient.connection.schedule;

import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * AppointmentServiceClient là một client Feign để kết nối với dịch vụ lịch hẹn.
 * Nó cung cấp các phương thức để gọi các endpoint của dịch vụ lịch hẹn.
 */
@FeignClient(name = "appointment-service")
public interface AppointmentServiceClient {
    /**
     * Gọi endpoint để lấy danh sách các ca làm việc còn trống.
     *
     * @param queryScheduleDto thông tin truy vấn lịch hẹn
     * @return ResponseEntity chứa danh sách các ca làm việc còn trống
     */
    @PostMapping("/api/schedule/available/get")
    ResponseEntity<List<AvailableTimeSlotDto>> getAvailable(
            @RequestBody QueryScheduleDto queryScheduleDto
    );
}
