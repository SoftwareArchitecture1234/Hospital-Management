package com.hms.patient.controller;

import com.hms.patient.common.CustomApiResponse;
import com.hms.patient.common.PageResponse;
import com.hms.patient.dtos.treatment.medicalHistory.*;
import com.hms.patient.service.treatmentService.medicalHistoryService.MedicalHistoryServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/medical-history")
@Tag(name = "Medical History Controller", description = "This controller manages medical history records")
@AllArgsConstructor
public class MedicalHistoryController {

    private final MedicalHistoryServiceInterface medicalHistoryService;

    @Operation(
            summary = "Get list of medical histories",
            description = "Returns a list of medical histories based on query parameters"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved medical histories",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping
    public ResponseEntity<PageResponse<MedicalHistoryDto>> listMedicalHistories(
            @RequestParam(required = false) Integer patientId,
            @RequestParam(required = false) Integer doctorId,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {

        MedicalHistoryQueryDto queryDto = new MedicalHistoryQueryDto();

        if (patientId != null) {
            queryDto.setPatientId(patientId);
        }

        if (doctorId != null) {
            queryDto.setDoctorId(doctorId);
        }

        queryDto.setFromDate(fromDate);
        queryDto.setToDate(toDate);
        queryDto.setPage(page);
        queryDto.setLimit(limit);

        PageResponse<MedicalHistoryDto> pagedResponse = medicalHistoryService.listMedicalHistories(queryDto);

        return ResponseEntity.ok(pagedResponse);
    }

    @Operation(
            summary = "Get medical history by ID",
            description = "Returns detailed information about a specific medical history"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved medical history",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MedicalHistoryDetailDto.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Medical history not found",
            content = @Content
    )
    @GetMapping("/detail")
    public ResponseEntity<MedicalHistoryDetailDto> getMedicalHistoryById(
            @RequestParam int patientId,
            @RequestParam int doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateModify) {

        MedicalHistoryDetailDto medicalHistory = medicalHistoryService.getMedicalHistoryById(
                patientId, doctorId, dateModify);

        return ResponseEntity.ok(medicalHistory);
    }

    @Operation(
            summary = "Create a new medical history",
            description = "Creates a new medical history with related medicines and symptoms"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created medical history",
            content = @Content
    )
    @PostMapping
    public ResponseEntity<Void> createMedicalHistory(@RequestBody MedicalHistoryCreateDto createDto) {
        medicalHistoryService.createMedicalHistory(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update medical history",
            description = "Updates an existing medical history's information"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully updated medical history",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Medical history not found",
            content = @Content
    )
    @PutMapping
    public ResponseEntity<MedicalHistoryDto> updateMedicalHistory(
            @RequestBody MedicalHistoryUpdateDto updateDto) {

        MedicalHistoryDto updatedMedicalHistory = medicalHistoryService.updateMedicalHistory(updateDto);
        return ResponseEntity.ok(updatedMedicalHistory);
    }

    @Operation(
            summary = "Delete medical history",
            description = "Deletes a medical history by its ID"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted medical history",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Medical history not found",
            content = @Content
    )
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMedicalHistory(
            @RequestParam int patientId,
            @RequestParam int doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateModify) {

        medicalHistoryService.deleteMedicalHistory(patientId, doctorId, dateModify);
        return ResponseEntity.noContent().build();
    }
}