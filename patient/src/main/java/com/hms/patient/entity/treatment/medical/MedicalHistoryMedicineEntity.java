package com.hms.patient.entity.treatment.medical;

import com.hms.patient.entity.treatment.MedicalHistory;
import com.hms.patient.entity.treatment.id.MedicalHistoryMedicineId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "medical_history_medicine")
@IdClass(MedicalHistoryMedicineId.class)
public class MedicalHistoryMedicineEntity implements Serializable {

    @Id
    @Column(name = "patient_id")
    private int patientId;

    @Id
    @Column(name = "doctor_id")
    private int doctorId;

    @Id
    @Column(name = "date_modify")
    private LocalDateTime dateModify;

    @Id
    @Column(name = "medicine_id")
    private int medicineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false),
            @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false),
            @JoinColumn(name = "date_modify", referencedColumnName = "date_modify", insertable = false, updatable = false)
    })
    private MedicalHistory medicalHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", referencedColumnName = "medicineId", insertable = false, updatable = false)
    private MedicineEntity medicineEntity;
}
