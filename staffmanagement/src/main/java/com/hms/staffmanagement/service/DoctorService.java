package com.hms.staffmanagement.service;

import com.hms.staffmanagement.dto.DoctorDto;
import com.hms.staffmanagement.entity.Doctor;
import com.hms.staffmanagement.entity.User;
import com.hms.staffmanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public void createDoctor(DoctorDto doctorDto) {
        User user = new User();
        user.setUserId(doctorDto.getUserId());
        Doctor doctor = Doctor.builder()
                .user(user)
                .specialized(doctorDto.getSpecialized())
                .build();

        doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findByDoctorId(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, DoctorDto updatedDoctor) {
        Doctor existingDoctor = getDoctorById(id);
        existingDoctor.setSpecialized(updatedDoctor.getSpecialized());
        return doctorRepository.save(existingDoctor);
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }
}