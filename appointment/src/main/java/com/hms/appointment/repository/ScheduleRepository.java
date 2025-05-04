package com.hms.appointment.repository;

import com.hms.appointment.entity.schedule.ScheduleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    @Query(nativeQuery = true,
            value = """
            SELECT * FROM schedule s
            WHERE s.date = :date
            AND s.start_time >= :startTime
            OR s.end_time <= :endTime
            AND s.doctor_id = :doctorId"""
    )
    ScheduleEntity findScheduleByDoctorIdAndDate(
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            int doctorId
    );

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = """
            DELETE FROM schedule 
            WHERE schedule_id = (
                SELECT schedule_id FROM (
                SELECT schedule_id
                FROM schedule 
                WHERE doctor_id = :doctor_id AND patient_id = :patient_id
                ORDER BY start_time DESC
                LIMIT 1
                ) AS sub
            )
            """
    )
    void cancelSchedule(
            int patient_id,
            int doctor_id
    );
}
