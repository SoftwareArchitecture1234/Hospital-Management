package com.hms.doctor.repository.workload;

import com.hms.doctor.entity.workload.WorkloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkloadRepository extends JpaRepository<WorkloadEntity, Long> {

    // Lấy toàn bộ workload theo doctorId
    List<WorkloadEntity> findByDoctorId(Integer doctorId);

    // Lấy workload theo ngày
    List<WorkloadEntity> findByDateBetween(LocalDate start, LocalDate end);

    List<WorkloadEntity> findByDoctorIdAndDateBetween(Integer doctorId, LocalDate start, LocalDate end);

    // Tổng hợp số lượt làm việc và tổng thời gian theo doctor
    @Query(value = """
            SELECT
                doctor_id,
                COUNT(*) AS total_shifts,
                SUM(TIMESTAMPDIFF(MINUTE, start_time, end_time)) AS total_minutes
            FROM workload
            WHERE date BETWEEN :start AND :end
            GROUP BY doctor_id
            """, nativeQuery = true)
    List<Object[]> summarizeWorkload(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);
}
