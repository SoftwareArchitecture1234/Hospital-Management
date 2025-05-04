package com.hms.doctor.repository.workload;

import com.hms.doctor.entity.workload.WorkloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkloadRepository extends JpaRepository<WorkloadEntity, Integer> {

  // Lấy toàn bộ workload theo doctorId
  List<WorkloadEntity> findByDoctorId(int doctorId);

  // Lấy workload theo thời gian
  List<WorkloadEntity> findByTimeBetween(LocalDateTime start, LocalDateTime end); // trong khoảng thời gian

  List<WorkloadEntity> findByDoctorIdAndTimeBetween(int doctorId, LocalDateTime start, LocalDateTime end); // cho 1 bác
                                                                                                           // sĩ

  // Tổng hợp số lần làm việc và tổng thời gian theo doctor trong khoảng thời gian
  @Query(value = """
      SELECT doctor_id, COUNT(*), 'Tổng số lượt làm việc trong khoảng thời gian'
      FROM Workload
      WHERE time BETWEEN :start AND :end
      GROUP BY doctor_id
      """, nativeQuery = true)
  List<Object[]> summarizeWorkload(
      @Param("start") LocalDateTime start,
      @Param("end") LocalDateTime end);

}
