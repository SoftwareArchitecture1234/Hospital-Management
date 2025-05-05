package com.hms.doctor.dto.workload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkloadSummaryDTO {
    private Integer doctorId;
    private Long totalShifts;
    private Long totalMinutes;
}
