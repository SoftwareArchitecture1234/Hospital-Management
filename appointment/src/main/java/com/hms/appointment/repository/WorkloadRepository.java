package com.hms.appointment.repository;


import com.hms.appointment.entity.workload.WorkloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface WorkloadRepository extends JpaRepository<WorkloadEntity, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM workload w " +
                    "WHERE w.date = ?1 " +
                    "AND w.start_time <= ?2 " +
                    "AND w.end_time >= ?3 " +
                    "AND w.shift = ?4 " +
                    "AND w.type_of_work = ?5")
    List<WorkloadEntity> findAvailableWorkload(
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            String shift,
            String typeOfWork
    );

    @Query(nativeQuery = true,
            value = "SELECT * FROM workload w " +
                    "WHERE w.doctor_id = ?1 " +
                    "AND w.date = ?2 " +
                    "AND w.start_time <= ?3 " +
                    "AND w.end_time >= ?4 " +
                    "AND w.type_of_work = ?5")
    Optional<WorkloadEntity> findAvailableWorkloadByDoctorId(
            int doctorId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            String typeOfWork
    );
}
