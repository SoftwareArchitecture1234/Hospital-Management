package com.hms.doctor.service.workload;

import com.hms.doctor.entity.workload.WorkloadEntity;

import java.time.LocalDate;
import java.util.List;

public interface WorkloadService {
  List<WorkloadEntity> getAllWorkloads();

  WorkloadEntity getWorkloadById(Long id);

  List<WorkloadEntity> getWorkloadsByDoctorId(Integer doctorId);

  List<WorkloadEntity> getWorkloadsByDateBetween(LocalDate start, LocalDate end);

  List<WorkloadEntity> getWorkloadsByDoctorAndDateRange(Integer doctorId, LocalDate start, LocalDate end);

  List<Object[]> summarizeWorkload(LocalDate startDate, LocalDate endDate);

  WorkloadEntity createWorkload(WorkloadEntity workload);

  void deleteWorkload(Long id);
}
