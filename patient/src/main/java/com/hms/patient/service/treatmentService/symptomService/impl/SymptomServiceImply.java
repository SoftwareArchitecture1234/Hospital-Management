package com.hms.patient.service.treatmentService.symptomService.impl;

import com.hms.patient.dtos.treatment.symptom.SymptomDto;
import com.hms.patient.dtos.treatment.symptom.SymptomQueryDto;
import com.hms.patient.entity.treatment.symptom.SymptomEntity;
import com.hms.patient.exception.ExceptionResourceNotFound;
import com.hms.patient.mapper.symptom.SymptomMapper;
import com.hms.patient.repository.SymptomRepository;
import com.hms.patient.service.treatmentService.symptomService.SymptomServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SymptomServiceImply implements SymptomServiceInterface {

    private final SymptomRepository symptomRepository;

    @Override
    public List<SymptomDto> listSymptoms(SymptomQueryDto symptomQueryDto) {
        String symptomName = symptomQueryDto.getSymptomName();

        if (symptomName != null && !symptomName.isEmpty()) {
            return new ArrayList<>(symptomRepository.findBySymptomNameContaining(symptomName));
        }

        return symptomRepository.findAll()
                .stream()
                .map(SymptomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SymptomDto getSymptomById(int symptomId) {
        SymptomEntity symptomEntity = symptomRepository
                .findById(symptomId)
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "Symptom",
                        "symptomId",
                        symptomId
                ));

        return SymptomMapper.toDto(symptomEntity);
    }

    @Override
    public int createSymptom(SymptomDto symptomDto) {
        SymptomEntity symptomEntity = SymptomMapper.toEntity(symptomDto);

        if (symptomEntity != null) {
            SymptomEntity savedEntity = symptomRepository.save(symptomEntity);
            return savedEntity.getSymptomId();
        } else {
            throw new IllegalArgumentException("Symptom entity cannot be null");
        }
    }

    @Override
    public void updateSymptom(SymptomDto symptomDto) {
        SymptomEntity symptomEntity = symptomRepository
                .findById(symptomDto.getSymptomId())
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "Symptom",
                        "symptomId",
                        symptomDto.getSymptomId()
                ));

        this.updateSymptomFields(symptomDto, symptomEntity);
        symptomRepository.save(symptomEntity);
    }

    @Override
    public void deleteSymptom(int symptomId) {
        if (!symptomRepository.existsById(symptomId)) {
            throw new ExceptionResourceNotFound(
                    "Symptom",
                    "symptomId",
                    symptomId
            );
        }

        symptomRepository.deleteById(symptomId);
    }

    private void updateSymptomFields(SymptomDto symptomDto, SymptomEntity symptomEntity) {
        if (symptomDto.getSymptomName() != null && !symptomDto.getSymptomName().isEmpty()) {
            symptomEntity.setSymptomName(symptomDto.getSymptomName());
        }

        if (symptomDto.getSymptomDetails() != null && !symptomDto.getSymptomDetails().isEmpty()) {
            symptomEntity.setSymptomDetails(symptomDto.getSymptomDetails());
        }
    }
}