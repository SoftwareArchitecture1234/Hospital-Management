package com.hms.patient.entity.treatment.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicalHistoryMedicineId implements Serializable {
    private int patientId;
    private int doctorId;
    private LocalDateTime dateModify;
    private int medicineId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalHistoryMedicineId)) return false;
        MedicalHistoryMedicineId that = (MedicalHistoryMedicineId) o;
        return patientId == that.patientId &&
                doctorId == that.doctorId &&
                medicineId == that.medicineId &&
                Objects.equals(dateModify, that.dateModify);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, doctorId, dateModify, medicineId);
    }
}
