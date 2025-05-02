package com.hms.patient.mapper.symptom;

import com.hms.patient.dtos.treatment.symptom.SymptomDto;
import com.hms.patient.entity.treatment.symptom.SymptomEntity;

public class SymptomMapper {
    public static SymptomEntity toEntity(SymptomDto symptomDto) {
        if (symptomDto == null) {
            return null;
        }

        SymptomEntity symptomEntity = new SymptomEntity();
        symptomEntity.setSymptomId(symptomDto.getSymptomId());
        symptomEntity.setSymptomName(symptomDto.getSymptomName());
        symptomEntity.setSymptomDetails(symptomDto.getSymptomDetails());

        return symptomEntity;
    }

    public static SymptomDto toDto(SymptomEntity symptomEntity) {
        if (symptomEntity == null) {
            return null;
        }

        SymptomDto symptomDto = new SymptomDto();
        symptomDto.setSymptomId(symptomEntity.getSymptomId());
        symptomDto.setSymptomName(symptomEntity.getSymptomName());
        symptomDto.setSymptomDetails(symptomEntity.getSymptomDetails());

        return symptomDto;
    }
}