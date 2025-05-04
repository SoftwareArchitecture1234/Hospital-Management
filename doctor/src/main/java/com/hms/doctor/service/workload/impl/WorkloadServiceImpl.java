package com.hms.doctor.service.workload.impl;

import com.hms.doctor.entity.workload.WorkloadEntity;
import com.hms.doctor.repository.workload.WorkloadRepository;
import com.hms.doctor.service.workload.WorkloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkloadServiceImpl implements WorkloadService {

    private final WorkloadRepository workloadRepository;

    @Autowired
    public WorkloadServiceImpl(WorkloadRepository workloadRepository) {
        this.workloadRepository = workloadRepository;
    }

    @Override
    public List<WorkloadEntity> getAllWorkloads() {
        return workloadRepository.findAll();
    }

    @Override
    public WorkloadEntity getWorkloadById(int id) {
        return workloadRepository.findById(id).orElse(null);
    }

    @Override
    public List<WorkloadEntity> getWorkloadsByDoctorId(int doctorId) {
        return workloadRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<WorkloadEntity> getWorkloadsByTimeBetween(LocalDateTime start, LocalDateTime end) {
        return workloadRepository.findByTimeBetween(start, end);
    }

    @Override
    public List<WorkloadEntity> getWorkloadsByDoctorAndDateRange(int doctorId, LocalDateTime start, LocalDateTime end) {
        return workloadRepository.findByDoctorIdAndTimeBetween(doctorId, start, end);
    }

    @Override
    public List<Object[]> summarizeWorkload(LocalDateTime startDate, LocalDateTime endDate) {
        return workloadRepository.summarizeWorkload(startDate, endDate);
    }

    @Override
    public WorkloadEntity createWorkload(WorkloadEntity workload) {
        return workloadRepository.save(workload);
    }

    @Override
    public void deleteWorkload(int id) {
        workloadRepository.deleteById(id);
    }
}
