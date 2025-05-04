package com.hms.doctor.service.workload;

import com.hms.doctor.entity.workload.WorkloadEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkloadService {
  List<WorkloadEntity> getAllWorkloads();

  WorkloadEntity getWorkloadById(int id);

  List<WorkloadEntity> getWorkloadsByDoctorId(int doctorId);

  List<WorkloadEntity> getWorkloadsByTimeBetween(LocalDateTime start, LocalDateTime end);

  List<WorkloadEntity> getWorkloadsByDoctorAndDateRange(int doctorId, LocalDateTime start, LocalDateTime end);

  List<Object[]> summarizeWorkload(LocalDateTime startDate, LocalDateTime endDate);

  WorkloadEntity createWorkload(WorkloadEntity workload);

  void deleteWorkload(int id);
}
