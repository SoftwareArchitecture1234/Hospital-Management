package com.hms.appointment.repository;

import com.hms.appointment.entity.schedule.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
