package com.hms.patient.controller;

import com.hms.patient.dtos.schedule.AvailableTimeSlotDto;
import com.hms.patient.dtos.schedule.QueryScheduleDto;
import com.hms.patient.dtos.schedule.RequestScheduleDto;
import com.hms.patient.service.connection.messagingrabbitmq.EventPublisherInterface;
import com.hms.patient.service.scheduleService.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Gửi yêu cầu lịch hẹn.
     * @param request thông tin lịch hẹn
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và thông báo thành công
     */
    @Operation(
            summary = "Gửi yêu cầu lịch hẹn",
            description = "Gửi yêu cầu lịch hẹn với thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "request",
            description = "Thông tin lịch hẹn",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = RequestScheduleDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Yêu cầu lịch hẹn được gửi thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = String.class
                    )
            )
    )
    @PostMapping("/request")
    public ResponseEntity<String> requestSchedule(@RequestBody RequestScheduleDto request) {
        request.setMessageType("CREATE");
        scheduleService.sendScheduleMessage(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Request schedule successfully");
    }

    /**
     * Gửi yêu cầu xóa lịch hẹn.
     * @param cancel thông tin lịch hẹn
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và thông báo thành công
     */
    @Operation(
            summary = "Gửi yêu cầu xóa lịch hẹn",
            description = "Gửi yêu cầu xóa lịch hẹn với thông tin được cung cấp trong request body (Lịch hẹn gần nhất của patient với doctor)."
    )
    @Parameter(
            name = "cancel",
            description = "Thông tin lịch hẹn: patient và doctor",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = RequestScheduleDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Yêu cầu xóa lịch hẹn được gửi thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = String.class
                    )
            )
    )




    @PostMapping("/cancel")
    public ResponseEntity<String> cancelSchedule(@RequestBody RequestScheduleDto request) {
        request.setMessageType("CANCEL");
        scheduleService.sendScheduleMessage(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Cancel schedule successfully");
    }

/**
     * Gửi yêu cầu tái tạo lịch hẹn.
     * @param reschedule thông tin lịch hẹn
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và thông báo thành công
     */
    @Operation(
            summary = "Gửi yêu cầu tái tạo lịch hẹn",
            description = "Gửi yêu cầu tái tạo lịch hẹn với thông tin được cung cấp trong request body (xóa lịch hẹn gần nhất của patient và doctor sau đó tạo ra 1 lịch hẹn mới với thông tin cung cấp)."
    )
    @Parameter(
            name = "reschedule",
            description = "Thông tin lịch hẹn",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = RequestScheduleDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Yêu cầu tái tạo lịch hẹn được gửi thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = String.class
                    )
            )
    )

    @PostMapping("/reschedule")
    public ResponseEntity<String> reSchedule(@RequestBody RequestScheduleDto request) {
        request.setMessageType("RESCHEDULE");
        scheduleService.sendScheduleMessage(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Reschedule successfully");
    }
}
