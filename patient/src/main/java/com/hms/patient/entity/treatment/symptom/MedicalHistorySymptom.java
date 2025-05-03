package com.hms.patient.entity.treatment.symptom;

import com.hms.patient.entity.treatment.MedicalHistory;
import com.hms.patient.entity.treatment.id.MedicalHistorySymptomId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@IdClass(MedicalHistorySymptomId.class)
@Table(name = "medical_history_symptom")
public class MedicalHistorySymptom {
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
    @Column(name = "symptom_id")
    private int symptomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false),
            @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false),
            @JoinColumn(name = "date_modify", referencedColumnName = "date_modify", insertable = false, updatable = false)
    })
    private MedicalHistory medicalHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symptom_id", insertable = false, updatable = false)
    private SymptomEntity symptomEntity;
}