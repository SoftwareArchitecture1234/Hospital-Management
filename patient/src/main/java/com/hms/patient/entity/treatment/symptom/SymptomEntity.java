package com.hms.patient.entity.treatment.symptom;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "Symptom")
@Entity
@Getter @Setter
public class SymptomEntity {
    @Id
    @Column(name = "symptom_id")
    private int symptomId;

    @Column(name = "symptom_name")
    private String symptomName;

    @Column(name = "symptom_details")
    private String symptomDetails;

    @OneToMany(mappedBy = "symptomEntity", fetch = FetchType.LAZY)
    private List<MedicalHistorySymptom> medicalHistorySymptoms;

}
