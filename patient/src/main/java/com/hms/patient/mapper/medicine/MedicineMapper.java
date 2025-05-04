package com.hms.patient.mapper.medicine;

import com.hms.patient.dtos.treatment.medicine.MedicineDto;
import com.hms.patient.entity.treatment.medical.MedicineEntity;

public class MedicineMapper {
    public static MedicineEntity toEntity(MedicineDto medicineDto) {
        if (medicineDto == null) {
            return null;
        }

        MedicineEntity medicineEntity = new MedicineEntity();
        medicineEntity.setMedicineId(medicineDto.getMedicineId());
        medicineEntity.setMedicineName(medicineDto.getMedicineName());
        medicineEntity.setMedicineType(medicineDto.getMedicineType());
        medicineEntity.setMedicineDetails(medicineDto.getMedicineDetails());
        medicineEntity.setAmount(medicineDto.getAmount() != null ? medicineDto.getAmount() : 0);

        return medicineEntity;
    }

    public static MedicineDto toDto(MedicineEntity medicineEntity) {
        if (medicineEntity == null) {
            return null;
        }

        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setMedicineId(medicineEntity.getMedicineId());
        medicineDto.setMedicineName(medicineEntity.getMedicineName());
        medicineDto.setMedicineType(medicineEntity.getMedicineType());
        medicineDto.setMedicineDetails(medicineEntity.getMedicineDetails());
        medicineDto.setAmount(medicineEntity.getAmount() != null ? medicineEntity.getAmount() : 0);

        return medicineDto;
    }
}