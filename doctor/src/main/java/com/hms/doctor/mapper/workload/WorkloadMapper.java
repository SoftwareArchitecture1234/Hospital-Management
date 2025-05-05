package com.hms.doctor.mapper.workload;

import com.hms.doctor.dto.workload.WorkloadDTO;
import com.hms.doctor.dto.workload.WorkloadSummaryDTO;
import com.hms.doctor.entity.workload.WorkloadEntity;

import java.util.List;
import java.util.stream.Collectors;

public class WorkloadMapper {

  public static WorkloadDTO toDTO(WorkloadEntity entity) {
    WorkloadDTO dto = new WorkloadDTO();
    dto.setWorkloadId(entity.getWorkloadId());
    dto.setDoctorId(entity.getDoctorId());
    dto.setShift(entity.getShift());
    dto.setDayOfWeek(entity.getDayOfWeek());
    dto.setTypeOfWork(entity.getTypeOfWork());
    dto.setRoom(entity.getRoom());
    dto.setStartTime(entity.getStartTime());
    dto.setEndTime(entity.getEndTime());
    dto.setDate(entity.getDate());
    dto.setNote(entity.getNote());
    return dto;
  }

  public static List<WorkloadDTO> toDTOList(List<WorkloadEntity> entities) {
    return entities.stream().map(WorkloadMapper::toDTO).collect(Collectors.toList());
  }

  public static WorkloadEntity toEntity(WorkloadDTO dto) {
    WorkloadEntity entity = new WorkloadEntity();
    entity.setWorkloadId(dto.getWorkloadId());
    entity.setDoctorId(dto.getDoctorId());
    entity.setShift(dto.getShift());
    entity.setDayOfWeek(dto.getDayOfWeek());
    entity.setTypeOfWork(dto.getTypeOfWork());
    entity.setRoom(dto.getRoom());
    entity.setStartTime(dto.getStartTime());
    entity.setEndTime(dto.getEndTime());
    entity.setDate(dto.getDate());
    entity.setNote(dto.getNote());
    return entity;
  }

  public static WorkloadSummaryDTO toSummaryDTO(Object[] rawData) {
    return new WorkloadSummaryDTO(
        ((Number) rawData[0]).intValue(),
        ((Number) rawData[1]).longValue(),
        ((Number) rawData[2]).longValue());
  }

  public static List<WorkloadSummaryDTO> toSummaryDTOList(List<Object[]> dataList) {
    return dataList.stream()
        .map(WorkloadMapper::toSummaryDTO)
        .collect(Collectors.toList());
  }
}
