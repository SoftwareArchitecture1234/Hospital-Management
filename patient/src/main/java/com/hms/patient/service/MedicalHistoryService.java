package com.hms.patient.service;

import com.hms.patient.entity.MedicalHistory;
import com.hms.patient.exception.ExceptionResourceNotFound;
import com.hms.patient.repository.MedicalHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MedicalHistoryService {

    @Autowired
    private MedicalHistoryRepo medicalHistoryRepo;

    public MedicalHistory getMedicalHistoryById(Long id) {
        return medicalHistoryRepo.findById(id)
                .orElseThrow(() -> new ExceptionResourceNotFound("MedicalHistory", "id", id));
    }

    public Page<MedicalHistory> getMedicalHistoriesByParams(Long patientId, Date fromDate, Date toDate, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);

        if (patientId == null) {
            return medicalHistoryRepo.findAll(pageable);
        } else {
            return medicalHistoryRepo.findByPatientIdAndDateRange(patientId, fromDate, toDate, pageable);
        }
    }

    public MedicalHistory saveMedicalHistory(MedicalHistory medicalHistory) {
        return medicalHistoryRepo.save(medicalHistory);
    }

    public void deleteMedicalHistory(Long id) {
        medicalHistoryRepo.deleteById(id);
    }
}