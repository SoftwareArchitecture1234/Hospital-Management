package com.hms.doctor.service.workload.impl;

import com.hms.doctor.entity.workload.WorkloadEntity;
import com.hms.doctor.repository.workload.WorkloadRepository;
import com.hms.doctor.service.workload.WorkloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public WorkloadEntity getWorkloadById(Long id) {
        return workloadRepository.findById(id).orElse(null);
    }

    @Override
    public List<WorkloadEntity> getWorkloadsByDoctorId(Integer doctorId) {
        return workloadRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<WorkloadEntity> getWorkloadsByDateBetween(LocalDate start, LocalDate end) {
        return workloadRepository.findByDateBetween(start, end);
    }

    @Override
    public List<WorkloadEntity> getWorkloadsByDoctorAndDateRange(Integer doctorId, LocalDate start, LocalDate end) {
        return workloadRepository.findByDoctorIdAndDateBetween(doctorId, start, end);
    }

    @Override
    public List<Object[]> summarizeWorkload(LocalDate startDate, LocalDate endDate) {
        return workloadRepository.summarizeWorkload(startDate, endDate);
    }

    @Override
    public WorkloadEntity createWorkload(WorkloadEntity workload) {
        return workloadRepository.save(workload);
    }

    @Override
    public void deleteWorkload(Long id) {
        workloadRepository.deleteById(id);
    }
}
