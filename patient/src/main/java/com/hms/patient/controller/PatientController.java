package com.hms.patient.controller;

import com.hms.patient.dtos.accounts.PatientDto;
import com.hms.patient.dtos.accounts.PatientInfoDto;
import com.hms.patient.dtos.accounts.PatientQueryDto;
import com.hms.patient.service.patientService.PatientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientServiceInterface patientService;

    @PostMapping("/create")
    public ResponseEntity<String> createPatient(@RequestBody PatientDto patientDto) {
        patientService.registerPatient(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Patient created successfully");
    }

    @PostMapping("/get")
    public ResponseEntity<PatientInfoDto> getPatient(@RequestBody PatientQueryDto patientQueryDto) {
        PatientInfoDto patientInfoDto = patientService.getPatient(patientQueryDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(patientInfoDto);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@RequestBody PatientDto patientDto) {
        patientService.updatePatient(patientDto);
        return ResponseEntity.ok("Patient updated successfully");
    }
}
