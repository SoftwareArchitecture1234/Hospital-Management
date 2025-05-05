package com.hms.doctor.repository.doctor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.doctor.entity.user.doctor.Doctor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
    Optional<Doctor> findByDoctorId(Integer doctorId);
}