package com.hms.doctor.dto.workload;

import java.time.LocalDateTime;

import com.hms.doctor.constant.WorkloadStatus;
import lombok.Data;

@Data
public class WorkloadRequestDto {
  private int doctorId;
  private String dateOfWeek;
  private String typeOfWork;
  private String shift;
  private LocalDateTime time;
  private String room;
  private String note;
  private WorkloadStatus status;
}
