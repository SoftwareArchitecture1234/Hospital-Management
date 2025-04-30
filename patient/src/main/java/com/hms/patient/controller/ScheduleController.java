package com.hms.patient.controller;

import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;
import com.hms.patient.service.scheduleService.IScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/patient/schedule")
@AllArgsConstructor
public class ScheduleController {
    private final IScheduleService scheduleService;

    @PostMapping("/get")
    public ResponseEntity<List<AvailableTimeSlotDto>> getListSchedule(@RequestBody QueryScheduleDto queryScheduleDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(scheduleService.getAvailableTimeSlots(queryScheduleDto));
    }
}
