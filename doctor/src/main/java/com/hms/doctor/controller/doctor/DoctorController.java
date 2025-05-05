package com.hms.doctor.controller.doctor;

import com.hms.doctor.dto.doctor.DoctorDto;
import com.hms.doctor.entity.user.doctor.Doctor;
import com.hms.doctor.service.doctor.DoctorServiceInterface;
import com.hms.doctor.service.doctor.impl.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    @Autowired
    private DoctorServiceInterface doctorService;

    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody DoctorDto doctorDto) {
        doctorService.createDoctor(doctorDto);
        return ResponseEntity.ok("Doctor created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Integer id, @RequestBody DoctorDto doctorDto) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}