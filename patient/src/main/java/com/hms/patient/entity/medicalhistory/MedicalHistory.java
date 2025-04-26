package com.hms.patient.entity.medicalhistory;

import com.hms.patient.entity.TempPatient;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private TempPatient patient;

    private Date date;
    private String diagnosis;
    private String treatment;
    private String notes;

}
