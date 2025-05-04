//package com.hms.patient.service;
//
//import com.hms.patient.entity.TempPatient;
//import com.hms.patient.entity.medicalhistory.MedicalHistory;
//import com.hms.patient.entity.medicalhistory.MedicalHistoryCreateBody;
//import com.hms.patient.exception.ExceptionResourceNotFound;
//import com.hms.patient.repository.MedicalHistoryRepo;
//import com.hms.patient.repository.TempPatientRepo;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class MedicalHistoryService {
//
//    @Autowired
//    private MedicalHistoryRepo medicalHistoryRepository;
//    @Autowired
//    private TempPatientRepo tempPatientRepository;
//
//    public MedicalHistory getMedicalHistoryById(Long id) {
//        return medicalHistoryRepository.findById(id)
//                .orElseThrow(() -> new ExceptionResourceNotFound("MedicalHistory", "id", id));
//    }
//
//    public Page<MedicalHistory> getMedicalHistoriesByParams(Long patientId, Date fromDate, Date toDate, int page, int limit) {
//        Pageable pageable = PageRequest.of(page, limit);
//
//        if (patientId == null) {
//            return medicalHistoryRepository.findAll(pageable);
//        } else {
//            return medicalHistoryRepository.findByPatientIdAndDateRange(patientId, fromDate, toDate, pageable);
//        }
//    }
//
//    public MedicalHistory createMedicalHistory(MedicalHistoryCreateBody createBody) {
//        // Check if patient exists
//        TempPatient patient = tempPatientRepository.findById(Long.parseLong(createBody.getPatientId()))
//                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + createBody.getPatientId()));
//
//        // Create new medical history object
//        MedicalHistory medicalHistory = new MedicalHistory();
//        medicalHistory.setPatient(patient);
//        medicalHistory.setDiagnosis(createBody.getDiagnosis());
//        medicalHistory.setTreatment(createBody.getTreatment());
//        medicalHistory.setNotes(createBody.getNotes());
//
//        // Set current date and time
//        medicalHistory.setDate(new Date());
//
//        // Generate local ID if needed (assuming you have a method for this)
//        // medicalHistory.setLocalId(generateLocalId());
//
//        return medicalHistoryRepository.save(medicalHistory);
//    }
//
//    public void deleteMedicalHistory(Long id) {
//        medicalHistoryRepository.deleteById(id);
//    }
//}