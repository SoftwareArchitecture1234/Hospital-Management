package com.hms.doctor.mapper.workload;

import com.hms.doctor.dto.workload.WorkloadRequestDto;
import com.hms.doctor.dto.workload.WorkloadResponseDto;
import com.hms.doctor.dto.workload.WorkloadSummaryDto;
import com.hms.doctor.entity.workload.WorkloadEntity;

public class WorkloadMapper {

  public static WorkloadEntity toEntity(WorkloadRequestDto dto) {
    WorkloadEntity entity = new WorkloadEntity();
    entity.setDoctorId(dto.getDoctorId());
    entity.setDateOfWeek(dto.getDateOfWeek());
    entity.setTypeOfWork(dto.getTypeOfWork());
    entity.setShift(dto.getShift());
    entity.setTime(dto.getTime()); // giờ là LocalDateTime
    entity.setRoom(dto.getRoom());
    entity.setNote(dto.getNote());
    entity.setStatus(dto.getStatus());
    return entity;
  }

  public static WorkloadResponseDto toDto(WorkloadEntity entity) {
    WorkloadResponseDto dto = new WorkloadResponseDto();
    dto.setWorkloadId(entity.getWorkloadId());
    dto.setDoctorId(entity.getDoctorId());
    dto.setDateOfWeek(entity.getDateOfWeek());
    dto.setTypeOfWork(entity.getTypeOfWork());
    dto.setShift(entity.getShift());
    dto.setTime(entity.getTime()); // giờ là LocalDateTime
    dto.setRoom(entity.getRoom());
    dto.setNote(entity.getNote());
    dto.setStatus(entity.getStatus());
    return dto;
  }

  public static WorkloadSummaryDto toSummaryDto(Object[] summary) {
    WorkloadSummaryDto dto = new WorkloadSummaryDto();
    dto.setDoctorId((Integer) summary[0]);
    dto.setCount((Long) summary[1]);
    dto.setSummaryText((String) summary[2]); // Nếu custom query trả về chuỗi
    return dto;
  }
}
