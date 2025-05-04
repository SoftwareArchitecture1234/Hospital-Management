package com.hms.patient.controller;

import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;
import com.hms.patient.service.scheduleService.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * ScheduleController xử lý các HTTP request cho việc lấy danh sách lịch hẹn.
 * Nó cung cấp các endpoint cho việc lấy danh sách lịch hẹn.
 * @ scheduleService là một interface cung cấp các phương thức để xử lý các yêu cầu liên quan đến lịch hẹn.
 */
@Tag(
     name = "Schedule",
        description = "ScheduleController xử lý các HTTP" +
                " request cho việc lấy danh sách lịch hẹn."
                + " Nó cung cấp các endpoint cho việc lấy danh sách lịch hẹn."
)
@RestController
@RequestMapping("/api/patient/schedule")
@AllArgsConstructor
public class ScheduleController {
    private final IScheduleService scheduleService;

    /**
     * Lấy danh sách lịch hẹn.
     * @param queryScheduleDto thông tin truy vấn lịch hẹn
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và danh sách lịch hẹn
     */
    @Operation(
            summary = "Lấy danh sách về các ca làm việc còn trống",
            description = "Lấy danh sách ca làm việc với thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "queryScheduleDto",
            description = "Thông tin truy vấn lịch hẹn",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = QueryScheduleDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Các ca làm việc còn trống được lấy thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = AvailableTimeSlotDto.class
                    )
            )
    )
    @PostMapping("/get")
    public ResponseEntity<List<AvailableTimeSlotDto>> getListSchedule(@RequestBody QueryScheduleDto queryScheduleDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(scheduleService.getAvailableTimeSlots(queryScheduleDto));
    }
}
