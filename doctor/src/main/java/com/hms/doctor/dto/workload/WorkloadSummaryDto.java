package com.hms.doctor.dto.workload;

import lombok.Data;

@Data
public class WorkloadSummaryDto {
  private int doctorId;
  private long count;
  private String summaryText; // Ví dụ: "Tổng số ca sáng: 5, chiều: 3"
}
