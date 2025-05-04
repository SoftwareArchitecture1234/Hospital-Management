package com.hms.patient.entity.treatment.medical;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "Medicine")
@Entity
public class MedicineEntity {
    @Id
    private int medicineId;

    @Column(name = "medicine_name")
    private String medicineName;

    @Column(name = "medicine_type")
    private String medicineType;

    @Column(name = "medicine_details")
    private String medicineDetails;

    @Column(name = "amount")
    private int amount;

    @OneToMany(mappedBy = "medicineEntity", fetch = FetchType.LAZY)
    private List<MedicalHistoryMedicineEntity> medicalHistoryMedicines;

}
