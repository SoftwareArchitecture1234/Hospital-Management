package com.hms.doctor.service.doctor;

import com.hms.doctor.dto.doctor.DoctorDto;
import com.hms.doctor.entity.user.doctor.Doctor;

import java.util.List;

public interface DoctorServiceInterface {
    void createDoctor(DoctorDto doctorDto);

    List<Doctor> getAllDoctors();

    Doctor getDoctorById(Integer id);

    Doctor createDoctor(Doctor doctor);

    Doctor updateDoctor(Integer id, DoctorDto updatedDoctor);

    void deleteDoctor(Integer id);
}
