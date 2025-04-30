package com.hms.patient.connection.schedule;

import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "appointment-service")
public interface AppointmentServiceClient {
    @PostMapping("/api/schedule/available/get")
    ResponseEntity<List<AvailableTimeSlotDto>> getAvailable(
            @RequestBody QueryScheduleDto queryScheduleDto
    );
}
