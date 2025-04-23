package com.hms.patient.controller;

import com.hms.patient.common.PageResponse;
import com.hms.patient.entity.medicalhistory.MedicalHistory;
import com.hms.patient.entity.medicalhistory.MedicalHistoryCreateBody;
import com.hms.patient.exception.ExceptionResourceNotFound;
import com.hms.patient.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping
    public ResponseEntity<PageResponse<MedicalHistory>> getMedicalHistories(
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {

        Page<MedicalHistory> springPage = medicalHistoryService.getMedicalHistoriesByParams(
                patientId, fromDate, toDate, page, limit);

        return new ResponseEntity<>(new PageResponse<>(springPage), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getMedicalHistoryById(@PathVariable Long id) {
        MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryById(id);
        return ResponseEntity.ok(medicalHistory);
    }



    @PostMapping
    public ResponseEntity<MedicalHistory> createMedicalHistory(@RequestBody MedicalHistoryCreateBody createBody) {
        return new ResponseEntity<>(medicalHistoryService.createMedicalHistory(createBody), HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<MedicalHistory> updateMedicalHistory(@PathVariable Long id, @RequestBody MedicalHistory medicalHistory) {
//        try {
//            medicalHistoryService.getMedicalHistoryById(id);
//
//            medicalHistory.setId(id);
//            MedicalHistory updatedHistory = medicalHistoryService.createMedicalHistory(medicalHistory);
//            return new ResponseEntity<>(updatedHistory, HttpStatus.OK);
//        } catch (ExceptionResourceNotFound e) {
//            throw e;
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMedicalHistory(@PathVariable Long id) {
        medicalHistoryService.deleteMedicalHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}