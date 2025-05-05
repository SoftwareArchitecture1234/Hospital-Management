package com.hms.staffmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.staffmanagement.entity.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    Optional<Doctor> findByDoctorId(Long doctorId);
}
