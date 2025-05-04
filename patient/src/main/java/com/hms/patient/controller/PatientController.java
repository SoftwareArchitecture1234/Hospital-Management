package com.hms.patient.controller;

import com.hms.patient.dtos.accounts.PatientDto;
import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.dtos.accounts.PatientQueryDto;
import com.hms.patient.service.patientService.PatientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * PatientController xử lý các HTTP request.
 * Nó cung cấp các endpoint cho việc tạo, lấy và cập nhật thông tin bệnh nhân.
 * @ patientService là một interface cung cấp các phương thức để xử lý các yêu cầu liên quan đến bệnh nhân.
 */
@Tag(
        name = "Patient",
        description = "PatientController xử lý các HTTP" +
                " request cho việc tạo, lấy và cập nhật thông tin bệnh nhân."
                + " Nó cung cấp các endpoint cho việc tạo, lấy và cập nhật thông tin bệnh nhân."
)
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientServiceInterface patientService;

    /**
     * Tạo một bệnh nhân mới.
     * @param patientDto thông tin bệnh nhân
     * @return ResponseEntity với mã trạng thái HTTP 201 (Created)
     */
    @Operation(
            summary = "Tạo một bệnh nhân mới",
            description = "Tạo một bệnh nhân mới với thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "patientDto",
            description = "Thông tin bệnh nhân cần tạo",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = PatientDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "201",
            description = "Bệnh nhân được tạo thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = String.class
                    )
            )
    )
    @PostMapping("/create")
    public ResponseEntity<String> createPatient(@RequestBody PatientDto patientDto) {
        patientService.registerPatient(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Patient created successfully");
    }

    /**
     * Lấy thông tin bệnh nhân theo ID.
     * @param patientQueryDto thông tin truy vấn bệnh nhân
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và thông tin bệnh nhân
     */
    @Operation(
            summary = "Lấy thông tin bệnh nhân theo pattientQueryDto",
            description = "Lấy thông tin bệnh nhân theo ID được cung cấp trong request body."
    )
    @Parameter(
            name = "patientQueryDto",
            description = "Thông tin truy vấn bệnh nhân cần lấy",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = PatientQueryDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thông tin bệnh nhân được lấy thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = PatientInfoDto.class
                    )
            )
    )
    @PostMapping("/get")
    public ResponseEntity<PatientInfoDto> getPatient(@RequestBody PatientQueryDto patientQueryDto) {
        PatientInfoDto patientInfoDto = patientService.getPatient(patientQueryDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(patientInfoDto);
    }

    /**
     * Cập nhật thông tin bệnh nhân.
     * @param patientDto thông tin bệnh nhân
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK)
     */
    @Operation(
            summary = "Cập nhật thông tin bệnh nhân",
            description = "Cập nhật thông tin bệnh nhân với thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "patientDto",
            description = "Thông tin bệnh nhân cần cập nhật",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = PatientDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = String.class
                    )
            ),
            description = "Thông tin bệnh nhân được cập nhật thành công"
    )
    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@RequestBody PatientDto patientDto) {
        patientService.updatePatient(patientDto);
        return ResponseEntity.ok("Patient updated successfully");
    }
}
