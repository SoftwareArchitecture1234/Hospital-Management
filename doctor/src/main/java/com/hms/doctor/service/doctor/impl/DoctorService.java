package com.hms.doctor.service.doctor.impl;

import com.hms.doctor.dto.doctor.DoctorDto;
import com.hms.doctor.entity.user.doctor.Doctor;
import com.hms.doctor.repository.doctor.DoctorRepository;
import com.hms.doctor.service.doctor.DoctorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService implements DoctorServiceInterface {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void createDoctor(DoctorDto doctorDto) {
        Doctor doctor = Doctor.builder()
                .doctorId(doctorDto.getUserId())
                .specialties(doctorDto.getSpecialties())
                .build();

        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Integer id) {
        return doctorRepository.findByDoctorId(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Integer id, DoctorDto updatedDoctor) {
        Doctor existingDoctor = getDoctorById(id);
        existingDoctor.setSpecialties(updatedDoctor.getSpecialties());
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteDoctor(Integer id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }
}