package com.hms.doctor.service;

import com.hms.doctor.dto.DoctorDto;
import com.hms.doctor.entity.Doctor;
import com.hms.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public void createDoctor(DoctorDto doctorDto) {
        Doctor doctor = Doctor.builder()
                .doctorId(doctorDto.getUserId())
                .specialties(doctorDto.getSpecialties())
                .build();

        doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Integer id) {
        return doctorRepository.findByDoctorId(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Integer id, DoctorDto updatedDoctor) {
        Doctor existingDoctor = getDoctorById(id);
        existingDoctor.setSpecialties(updatedDoctor.getSpecialties());
        return doctorRepository.save(existingDoctor);
    }

    public void deleteDoctor(Integer id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }
}