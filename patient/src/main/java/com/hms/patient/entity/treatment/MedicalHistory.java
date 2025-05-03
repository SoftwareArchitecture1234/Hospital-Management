package com.hms.patient.entity.treatment;

import com.hms.patient.entity.treatment.id.MedicalHistoryId;
import com.hms.patient.entity.treatment.medical.MedicalHistoryMedicineEntity;
import com.hms.patient.entity.treatment.symptom.MedicalHistorySymptom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "Medical_History")
@IdClass(MedicalHistoryId.class)
public class MedicalHistory {
    @Id
    @Column(name = "patient_id")
    private int patientId;

    @Id
    @Column(name = "doctor_id")
    private int doctorId;

    @Id
    @Column(name= "date_modify", insertable = false, updatable = false)
    private LocalDateTime dateModify;

    @Column(name = "type_of_treatment")
    private String typeOfTreatment;

    @Column(name = "disease")
    private String disease;

    @Column(name = "notes")
    private String notes;

    @Column(name= "invoice_id")
    private String invoiceId;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id", insertable = false, updatable = false)
//    private InvoiceEntity invoiceEntity;

    @OneToMany(mappedBy = "medicalHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicalHistorySymptom> medicalHistorySymptoms;

    @OneToMany(mappedBy = "medicalHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicalHistoryMedicineEntity> medicalHistoryMedicines;
}