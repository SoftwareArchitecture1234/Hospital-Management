package com.hms.patient.service;

import com.hms.patient.entity.MedicalHistory;
import com.hms.patient.repository.MedicalHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {

    @Autowired
    private MedicalHistoryRepo medicalHistoryRepo;

    public List<MedicalHistory> getAllMedicalHistories() {
        return medicalHistoryRepo.findAll();
    }

    public Optional<MedicalHistory> getMedicalHistoryById(Long id) {
        return medicalHistoryRepo.findById(id);
    }

    public List<MedicalHistory> getMedicalHistoriesByPatientId(Long patientId) {
        return medicalHistoryRepo.findByPatientId(patientId);
    }

    public MedicalHistory saveMedicalHistory(MedicalHistory medicalHistory) {
        return medicalHistoryRepo.save(medicalHistory);
    }

    public void deleteMedicalHistory(Long id) {
        medicalHistoryRepo.deleteById(id);
    }
}