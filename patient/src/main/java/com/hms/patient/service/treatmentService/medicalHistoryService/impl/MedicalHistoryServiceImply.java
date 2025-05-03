package com.hms.patient.service.treatmentService.medicalHistoryService.impl;

import com.hms.patient.common.PageResponse;
import com.hms.patient.dtos.invoice.InvoiceDto;
import com.hms.patient.dtos.treatment.medicalHistory.*;
import com.hms.patient.dtos.treatment.medicine.MedicalHistoryMedicineDto;
import com.hms.patient.dtos.treatment.medicine.MedicineDto;
import com.hms.patient.dtos.treatment.symptom.MedicalHistorySymptomDto;
import com.hms.patient.dtos.treatment.symptom.SymptomDto;
import com.hms.patient.entity.treatment.MedicalHistory;
import com.hms.patient.entity.treatment.id.MedicalHistoryId;
import com.hms.patient.entity.treatment.medical.MedicalHistoryMedicineEntity;
import com.hms.patient.entity.treatment.symptom.MedicalHistorySymptom;
import com.hms.patient.exception.ExceptionResourceNotFound;
import com.hms.patient.mapper.medicalHistory.MedicalHistoryMapper;
import com.hms.patient.repository.MedicalHistoryMedicineRepository;
import com.hms.patient.repository.MedicalHistoryRepository;
import com.hms.patient.repository.MedicalHistorySymptomRepository;
import com.hms.patient.repository.MedicineRepository;
import com.hms.patient.repository.SymptomRepository;
import com.hms.patient.service.treatmentService.medicalHistoryService.MedicalHistoryServiceInterface;
import com.hms.patient.service.treatmentService.medicineService.MedicineServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hms.patient.service.invoiceService.invoiceService.InvoiceServiceInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalHistoryServiceImply implements MedicalHistoryServiceInterface {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final MedicalHistoryMedicineRepository medicalHistoryMedicineRepository;
    private final MedicalHistorySymptomRepository medicalHistorySymptomRepository;
    private final MedicineRepository medicineRepository;
    private final SymptomRepository symptomRepository;
    private final InvoiceServiceInterface  invoiceService;
    private final MedicineServiceInterface medicineService;

    @Override
    public PageResponse<MedicalHistoryDto> listMedicalHistories(MedicalHistoryQueryDto queryDto) {
        LocalDateTime fromDate = null;
        LocalDateTime toDate = null;

        // Process date range if provided
        if (queryDto.getFromDate() != null && !queryDto.getFromDate().isEmpty()
                && queryDto.getToDate() != null && !queryDto.getToDate().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            fromDate = LocalDate.parse(queryDto.getFromDate(), formatter).atStartOfDay();
            toDate = LocalDate.parse(queryDto.getToDate(), formatter).atTime(LocalTime.MAX);
        }

        // Create pageable request
        int page = Math.max(0, queryDto.getPage() - 1); // Convert from 1-based to 0-based
        Pageable pageable = PageRequest.of(page, queryDto.getLimit());

        // Get paged results
        Integer patientId = queryDto.getPatientId() > 0 ? queryDto.getPatientId() : null;
        Integer doctorId = queryDto.getDoctorId() > 0 ? queryDto.getDoctorId() : null;

        Page<MedicalHistoryDto> resultPage = medicalHistoryRepository.findMedicalHistoriesWithFilters(
                patientId, doctorId, fromDate, toDate, pageable);

        // Return the paged response
        return new PageResponse<>(resultPage);
    }

    @Override
    public MedicalHistoryDetailDto getMedicalHistoryById(int patientId, int doctorId, LocalDateTime dateModify) {
        MedicalHistoryId id = new MedicalHistoryId(patientId, doctorId, dateModify);
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "MedicalHistory",
                        "id",
                        patientId + "-" + doctorId + "-" + dateModify
                ));

        MedicalHistoryDetailDto detailDto = MedicalHistoryMapper.toDetailDto(medicalHistory);

        // Fetch and set symptoms
        List<MedicalHistorySymptomDto> symptomRelations = medicalHistorySymptomRepository.findByPatientId(patientId);
        List<SymptomDto> symptoms = new ArrayList<>();

        for (MedicalHistorySymptomDto relation : symptomRelations) {
            if (relation.getDoctorId() == doctorId && relation.getDateModify().equals(dateModify)) {
                Optional<SymptomDto> symptomDto = symptomRepository.findDtoById(relation.getSymptomId());
                symptomDto.ifPresent(symptoms::add);
            }
        }

        detailDto.setSymptoms(symptoms);

        // Fetch and set medicines
        List<MedicalHistoryMedicineDto> medicineRelations = medicalHistoryMedicineRepository.findByPatientId(patientId);
        List<MedicineDto> medicines = new ArrayList<>();

        for (MedicalHistoryMedicineDto relation : medicineRelations) {
            if (relation.getDoctorId() == doctorId && relation.getDateModify().equals(dateModify)) {
                Optional<MedicineDto> medicineDto = medicineRepository.findDtoById(relation.getMedicineId());
                medicineDto.ifPresent(medicines::add);
            }
        }

        detailDto.setMedicines(medicines);

        return detailDto;
    }

    @Override
    @Transactional
    public void createMedicalHistory(MedicalHistoryCreateDto createDto) {
        // Create invoice first
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setDateExport(LocalDateTime.now());
        invoiceDto.setCost(calculateCost(createDto)); // You'll need to implement this method
        invoiceDto.setPaymentMethod(createDto.getPaymentMethod()); // Assuming this field exists in MedicalHistoryCreateDto

        // Call invoice service to create the invoice
        String invoiceId = invoiceService.createInvoice(invoiceDto);

        // Create medical history record with the invoice ID
        MedicalHistory medicalHistory = MedicalHistoryMapper.toEntity(createDto);
        medicalHistory.setInvoiceId(invoiceId); // Assuming InvoiceId field exists in MedicalHistory entity

        // Save the medical history
        MedicalHistory savedMedicalHistory = medicalHistoryRepository.save(medicalHistory);

        // Create medicine relationships
        if (createDto.getMedicineIds() != null && !createDto.getMedicineIds().isEmpty()) {
            for (Integer medicineId : createDto.getMedicineIds()) {
                MedicalHistoryMedicineEntity medicineRelation = new MedicalHistoryMedicineEntity();
                medicineRelation.setPatientId(savedMedicalHistory.getPatientId());
                medicineRelation.setDoctorId(savedMedicalHistory.getDoctorId());
                medicineRelation.setDateModify(savedMedicalHistory.getDateModify());
                medicineRelation.setMedicineId(medicineId);

                medicalHistoryMedicineRepository.save(medicineRelation);
            }
        }

        // Create symptom relationships
        if (createDto.getSymptomIds() != null && !createDto.getSymptomIds().isEmpty()) {
            for (Integer symptomId : createDto.getSymptomIds()) {
                MedicalHistorySymptom symptomRelation = new MedicalHistorySymptom();
                symptomRelation.setPatientId(savedMedicalHistory.getPatientId());
                symptomRelation.setDoctorId(savedMedicalHistory.getDoctorId());
                symptomRelation.setDateModify(savedMedicalHistory.getDateModify());
                symptomRelation.setSymptomId(symptomId);

                medicalHistorySymptomRepository.save(symptomRelation);
            }
        }
    }

    /**
     * Calculate the cost for the medical history based on medicines and other factors
     * @param createDto The medical history creation data
     * @return The calculated cost
     */
    private double calculateCost(MedicalHistoryCreateDto createDto) {
        double cost = 0.0;

        // Base consultation fee
        cost += 200000; // Example base fee in VND

        // Add cost for each medicine if medicines are specified
        if (createDto.getMedicineIds() != null && !createDto.getMedicineIds().isEmpty()) {
            for (Integer medicineId : createDto.getMedicineIds()) {
                // Get medicine price from medicine service
                MedicineDto medicineDto = medicineService.getMedicineById(medicineId);
                // Add medicine cost (this is just an example, actual implementation would depend on your system)
                cost += 50000; // Example medicine cost
            }
        }

        // You can add more cost calculations based on other factors like treatments, procedures, etc.

        return cost;
    }

    @Override
    @Transactional
    public MedicalHistoryDto updateMedicalHistory(MedicalHistoryUpdateDto updateDto) {
        MedicalHistoryId id = new MedicalHistoryId(
                updateDto.getPatientId(),
                updateDto.getDoctorId(),
                updateDto.getDateModify()
        );

        MedicalHistory oldMedicalHistory = medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "MedicalHistory",
                        "id",
                        updateDto.getPatientId() + "-" + updateDto.getDoctorId() + "-" + updateDto.getDateModify()
                ));

        // Create a new record with updated date_modify
        LocalDateTime newDateModify = LocalDateTime.now();

        // Create a new medical history record with the updated information
        MedicalHistory newMedicalHistory = new MedicalHistory();
        newMedicalHistory.setPatientId(oldMedicalHistory.getPatientId());
        newMedicalHistory.setDoctorId(oldMedicalHistory.getDoctorId());
        newMedicalHistory.setDateModify(newDateModify);
        newMedicalHistory.setTypeOfTreatment(updateDto.getTypeOfTreatment() != null ?
                updateDto.getTypeOfTreatment() : oldMedicalHistory.getTypeOfTreatment());
        newMedicalHistory.setDisease(updateDto.getDisease() != null ?
                updateDto.getDisease() : oldMedicalHistory.getDisease());
        newMedicalHistory.setNotes(updateDto.getNotes() != null ?
                updateDto.getNotes() : oldMedicalHistory.getNotes());
        newMedicalHistory.setInvoiceId(updateDto.getInvoiceId() != null ?
                updateDto.getInvoiceId() : oldMedicalHistory.getInvoiceId());

        // Save the new medical history record
        MedicalHistory savedMedicalHistory = medicalHistoryRepository.save(newMedicalHistory);

        // Delete the old record after creating the new one
        medicalHistoryRepository.delete(oldMedicalHistory);

        // Handle medicine relationships
        if (updateDto.getMedicineIds() != null) {
            // Create new medicine relationships with the new date_modify
            for (Integer medicineId : updateDto.getMedicineIds()) {
                MedicalHistoryMedicineEntity medicineRelation = new MedicalHistoryMedicineEntity();
                medicineRelation.setPatientId(updateDto.getPatientId());
                medicineRelation.setDoctorId(updateDto.getDoctorId());
                medicineRelation.setDateModify(newDateModify); // Use new date_modify
                medicineRelation.setMedicineId(medicineId);

                medicalHistoryMedicineRepository.save(medicineRelation);
            }
        } else {
            // Copy existing medicine relationships with the new date_modify
            List<MedicalHistoryMedicineEntity> existingMedicines =
                    medicalHistoryMedicineRepository.findAllByPatientIdAndDoctorIdAndDateModify(
                            updateDto.getPatientId(),
                            updateDto.getDoctorId(),
                            updateDto.getDateModify()
                    );

            for (MedicalHistoryMedicineEntity existing : existingMedicines) {
                MedicalHistoryMedicineEntity medicineRelation = new MedicalHistoryMedicineEntity();
                medicineRelation.setPatientId(existing.getPatientId());
                medicineRelation.setDoctorId(existing.getDoctorId());
                medicineRelation.setDateModify(newDateModify); // Use new date_modify
                medicineRelation.setMedicineId(existing.getMedicineId());

                medicalHistoryMedicineRepository.save(medicineRelation);
            }
        }

        // Handle symptom relationships
        if (updateDto.getSymptomIds() != null) {
            // Create new symptom relationships with the new date_modify
            for (Integer symptomId : updateDto.getSymptomIds()) {
                MedicalHistorySymptom symptomRelation = new MedicalHistorySymptom();
                symptomRelation.setPatientId(updateDto.getPatientId());
                symptomRelation.setDoctorId(updateDto.getDoctorId());
                symptomRelation.setDateModify(newDateModify); // Use new date_modify
                symptomRelation.setSymptomId(symptomId);

                medicalHistorySymptomRepository.save(symptomRelation);
            }
        } else {
            // Copy existing symptom relationships with the new date_modify
            List<MedicalHistorySymptom> existingSymptoms =
                    medicalHistorySymptomRepository.findAllByPatientIdAndDoctorIdAndDateModify(
                            updateDto.getPatientId(),
                            updateDto.getDoctorId(),
                            updateDto.getDateModify()
                    );

            for (MedicalHistorySymptom existing : existingSymptoms) {
                MedicalHistorySymptom symptomRelation = new MedicalHistorySymptom();
                symptomRelation.setPatientId(existing.getPatientId());
                symptomRelation.setDoctorId(existing.getDoctorId());
                symptomRelation.setDateModify(newDateModify); // Use new date_modify
                symptomRelation.setSymptomId(existing.getSymptomId());

                medicalHistorySymptomRepository.save(symptomRelation);
            }
        }

        // Convert to DTO for the response
        MedicalHistoryDto responseDto = MedicalHistoryMapper.toDto(savedMedicalHistory);
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteMedicalHistory(int patientId, int doctorId, LocalDateTime dateModify) {
        MedicalHistoryId id = new MedicalHistoryId(patientId, doctorId, dateModify);

        if (!medicalHistoryRepository.existsById(id)) {
            throw new ExceptionResourceNotFound(
                    "MedicalHistory",
                    "id",
                    patientId + "-" + doctorId + "-" + dateModify
            );
        }

        // Delete related medicine records
        medicalHistoryMedicineRepository.deleteAllByPatientIdAndDoctorIdAndDateModify(
                patientId, doctorId, dateModify);

        // Delete related symptom records
        medicalHistorySymptomRepository.deleteAllByPatientIdAndDoctorIdAndDateModify(
                patientId, doctorId, dateModify);

        // Delete the medical history record
        medicalHistoryRepository.deleteById(id);
    }
}