package com.hms.patient.entity.treatment.id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryId implements Serializable {
    private int patientId;
    private int doctorId;
    private LocalDateTime dateModify;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalHistoryId that)) return false;
        return (
                patientId == that.patientId &&
                doctorId == that.doctorId &&
                dateModify.equals(that.dateModify)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, doctorId, dateModify);
    }
}
