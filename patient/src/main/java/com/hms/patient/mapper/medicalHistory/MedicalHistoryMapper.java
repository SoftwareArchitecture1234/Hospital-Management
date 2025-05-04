package com.hms.patient.mapper.medicalHistory;

import com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryCreateDto;
import com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryDetailDto;
import com.hms.patient.dtos.treatment.medicalHistory.MedicalHistoryDto;
import com.hms.patient.entity.treatment.MedicalHistory;

import java.time.LocalDateTime;

public class MedicalHistoryMapper {
    public static MedicalHistory toEntity(MedicalHistoryCreateDto dto) {
        if (dto == null) {
            return null;
        }

        MedicalHistory entity = new MedicalHistory();
        entity.setPatientId(dto.getPatientId());
        entity.setDoctorId(dto.getDoctorId());
        entity.setDateModify(LocalDateTime.now());
        entity.setTypeOfTreatment(dto.getTypeOfTreatment());
        entity.setDisease(dto.getDisease());
        entity.setNotes(dto.getNotes());
        entity.setInvoiceId(dto.getInvoiceId());

        return entity;
    }

    public static MedicalHistoryDto toDto(MedicalHistory entity) {
        if (entity == null) {
            return null;
        }

        return new MedicalHistoryDto(
                entity.getPatientId(),
                entity.getDoctorId(),
                entity.getDateModify(),
                entity.getTypeOfTreatment(),
                entity.getDisease(),
                entity.getNotes(),
                entity.getInvoiceId()
        );
    }

    public static MedicalHistoryDetailDto toDetailDto(MedicalHistory entity) {
        if (entity == null) {
            return null;
        }

        MedicalHistoryDetailDto dto = new MedicalHistoryDetailDto();
        dto.setPatientId(entity.getPatientId());
        dto.setDoctorId(entity.getDoctorId());
        dto.setDateModify(entity.getDateModify());
        dto.setTypeOfTreatment(entity.getTypeOfTreatment());
        dto.setDisease(entity.getDisease());
        dto.setNotes(entity.getNotes());
        dto.setInvoiceId(entity.getInvoiceId());

        return dto;
    }
}