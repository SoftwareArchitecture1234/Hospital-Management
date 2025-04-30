package com.hms.appointment.controller;

import com.hms.appointment.dtos.schedule.AvailableTimeSlotDto;
import com.hms.appointment.dtos.schedule.QueryScheduleDto;
import com.hms.appointment.service.IScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/schedule/available")
@AllArgsConstructor
public class ScheduleController {
    private final IScheduleService scheduleService;

    @PostMapping("/get")
    public ResponseEntity<List<AvailableTimeSlotDto>> getAvailable(@RequestBody QueryScheduleDto queryScheduleDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(scheduleService.getAvailableSlots(queryScheduleDto));
    }
}
