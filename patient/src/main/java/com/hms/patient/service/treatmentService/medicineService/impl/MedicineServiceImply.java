package com.hms.patient.service.treatmentService.medicineService.impl;

import com.hms.patient.dtos.treatment.medicine.MedicineDto;
import com.hms.patient.dtos.treatment.medicine.MedicineQueryDto;
import com.hms.patient.entity.treatment.medical.MedicineEntity;
import com.hms.patient.exception.ExceptionResourceNotFound;
import com.hms.patient.mapper.medicine.MedicineMapper;
import com.hms.patient.repository.MedicineRepository;
import com.hms.patient.service.treatmentService.medicineService.MedicineServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicineServiceImply implements MedicineServiceInterface {

    private final MedicineRepository medicineRepository;

    @Override
    public List<MedicineDto> listMedicines(MedicineQueryDto medicineQueryDto) {
        String medicineName = medicineQueryDto.getMedicineName();
        String medicineType = medicineQueryDto.getMedicineType();

        if ((medicineName != null && !medicineName.isEmpty()) ||
                (medicineType != null && !medicineType.isEmpty())) {
            return medicineRepository.findByMedicineNameContainingAndMedicineType(
                    medicineName != null ? medicineName : "",
                    medicineType);
        }

        return medicineRepository.findAll()
                .stream()
                .map(MedicineMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicineDto getMedicineById(int medicineId) {
        return medicineRepository.findDtoById(medicineId)
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "Medicine",
                        "medicineId",
                        medicineId
                ));
    }

    @Override
    public int createMedicine(MedicineDto medicineDto) {
        MedicineEntity medicineEntity = MedicineMapper.toEntity(medicineDto);

        if (medicineEntity != null) {
            MedicineEntity savedEntity = medicineRepository.save(medicineEntity);
            return savedEntity.getMedicineId();
        } else {
            throw new IllegalArgumentException("Medicine entity cannot be null");
        }
    }

    @Override
    public void updateMedicine(MedicineDto medicineDto) {
        MedicineEntity medicineEntity = medicineRepository
                .findById(medicineDto.getMedicineId())
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "Medicine",
                        "medicineId",
                        medicineDto.getMedicineId()
                ));

        updateMedicineFields(medicineDto, medicineEntity);
        medicineRepository.save(medicineEntity);
    }

    @Override
    public void deleteMedicine(int medicineId) {
        if (!medicineRepository.existsById(medicineId)) {
            throw new ExceptionResourceNotFound(
                    "Medicine",
                    "medicineId",
                    medicineId
            );
        }

        medicineRepository.deleteById(medicineId);
    }

    private void updateMedicineFields(MedicineDto medicineDto, MedicineEntity medicineEntity) {
        if (medicineDto.getMedicineName() != null && !medicineDto.getMedicineName().isEmpty()) {
            medicineEntity.setMedicineName(medicineDto.getMedicineName());
        }

        if (medicineDto.getMedicineType() != null && !medicineDto.getMedicineType().isEmpty()) {
            medicineEntity.setMedicineType(medicineDto.getMedicineType());
        }

        if (medicineDto.getMedicineDetails() != null && !medicineDto.getMedicineDetails().isEmpty()) {
            medicineEntity.setMedicineDetails(medicineDto.getMedicineDetails());
        }

        if (medicineDto.getAmount() > 0) {
            medicineEntity.setAmount(medicineDto.getAmount());
        }
    }
}