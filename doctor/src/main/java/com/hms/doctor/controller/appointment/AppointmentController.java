package com.hms.doctor.controller.appointment;


import com.hms.doctor.dto.schedule.RequestScheduleDto;
import com.hms.doctor.service.appointment.IAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AppointmentController là một lớp điều khiển trong ứng dụng Spring Boot,
 * nó xử lý các yêu cầu liên quan đến việc gửi thông báo xác nhận và hủy lịch hẹn.
 * Nó sử dụng IAppointmentService để thực hiện các thao tác liên quan đến lịch hẹn.
 */
@RestController
@RequestMapping("/api/v1/doctors/appointments")
@Tag(
        name = "Appointment",
        description = "AppointmentController xử lý các yêu cầu liên quan đến việc gửi thông báo xác nhận và hủy lịch hẹn."
)
public class AppointmentController {
    @Autowired
    private IAppointmentService appointmentService;

    /**
     * Gửi thông báo xác nhận lịch hẹn.
     *
     * @param requestScheduleDto thông tin lịch hẹn
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và thông báo thành công
     */
    @Operation(
            summary = "Gửi thông báo xác nhận lịch hẹn",
            description = "Gửi thông báo xác nhận lịch hẹn cho bác sĩ và bệnh nhân."
    )
    @Parameter(
            name = "requestScheduleDto",
            description = "Thông tin lịch hẹn",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = RequestScheduleDto.class
                    )
            )
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thông báo xác nhận lịch hẹn đã được gửi thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = String.class
                    )
            )
    )
    @PostMapping("/confirm")
    public ResponseEntity<String> sendConfirmationMessage(@RequestBody RequestScheduleDto requestScheduleDto) {
        appointmentService.confirmAppointment(requestScheduleDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Confirmation message sent successfully");
    }

    /**
     * Gửi thông báo hủy lịch hẹn.
     *
     * @param requestScheduleDto thông tin lịch hẹn
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và thông báo thành công
     */
    @Operation(
            summary = "Gửi thông báo hủy lịch hẹn",
            description = "Gửi thông báo hủy lịch hẹn cho bác sĩ và bệnh nhân."
    )
    @Parameter(
            name = "requestScheduleDto",
            description = "Thông tin lịch hẹn",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = RequestScheduleDto.class
                    )
            )
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thông báo hủy lịch hẹn đã được gửi thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = String.class
                    )
            )
    )
    @PostMapping("/cancel")
    public ResponseEntity<String> sendCancellationMessage(@RequestBody RequestScheduleDto requestScheduleDto) {
        appointmentService.cancelAppointment(requestScheduleDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Cancellation message sent successfully");
    }
}
