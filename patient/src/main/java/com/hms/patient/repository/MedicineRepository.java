package com.hms.patient.repository;

import com.hms.patient.dtos.treatment.medicine.MedicineDto;
import com.hms.patient.entity.treatment.medical.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {

    @Query("SELECT new com.hms.patient.dtos.treatment.medicine.MedicineDto(" +
            "m.medicineId, m.medicineName, m.medicineType, m.medicineDetails, m.amount) " +
            "FROM MedicineEntity m " +
            "WHERE m.medicineName LIKE %:name% " +
            "AND (:type IS NULL OR m.medicineType = :type)")
    List<MedicineDto> findByMedicineNameContainingAndMedicineType(
            @Param("name") String medicineName,
            @Param("type") String medicineType);

    @Query("SELECT new com.hms.patient.dtos.treatment.medicine.MedicineDto(" +
            "m.medicineId, m.medicineName, m.medicineType, m.medicineDetails, m.amount) " +
            "FROM MedicineEntity m " +
            "WHERE m.medicineId = :id")
    Optional<MedicineDto> findDtoById(@Param("id") int medicineId);
}