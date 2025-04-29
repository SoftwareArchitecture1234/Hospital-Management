package com.hms.patient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @PostMapping("/create")
    public ResponseEntity<String> createPatient() {
        return ResponseEntity.ok("Patient created successfully");
    }

    @PostMapping("/get")
    public ResponseEntity<String> getPatient() {
        return ResponseEntity.ok("Patient retrieved successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePatient() {
        return ResponseEntity.ok("Patient updated successfully");
    }
}
