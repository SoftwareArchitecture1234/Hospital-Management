package com.hms.patient.controller;

import com.hms.patient.entity.MedicalHistory;
import com.hms.patient.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping
    public ResponseEntity<List<MedicalHistory>> getAllMedicalHistories() {
        return new ResponseEntity<>(medicalHistoryService.getAllMedicalHistories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getMedicalHistoryById(@PathVariable Long id) {
        Optional<MedicalHistory> medicalHistory = medicalHistoryService.getMedicalHistoryById(id);
        return medicalHistory.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalHistory>> getMedicalHistoriesByPatientId(@PathVariable Long patientId) {
        return new ResponseEntity<>(medicalHistoryService.getMedicalHistoriesByPatientId(patientId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalHistory> createMedicalHistory(@RequestBody MedicalHistory medicalHistory) {
        return new ResponseEntity<>(medicalHistoryService.saveMedicalHistory(medicalHistory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistory> updateMedicalHistory(@PathVariable Long id, @RequestBody MedicalHistory medicalHistory) {
        return medicalHistoryService.getMedicalHistoryById(id)
                .map(existingHistory -> {
                    medicalHistory.setId(id);
                    return new ResponseEntity<>(medicalHistoryService.saveMedicalHistory(medicalHistory), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMedicalHistory(@PathVariable Long id) {
        medicalHistoryService.deleteMedicalHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}