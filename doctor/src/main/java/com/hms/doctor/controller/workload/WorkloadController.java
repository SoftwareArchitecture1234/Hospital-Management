package com.hms.doctor.controller.workload;

import com.hms.doctor.dto.workload.WorkloadRequestDto;
import com.hms.doctor.dto.workload.WorkloadResponseDto;
import com.hms.doctor.dto.workload.WorkloadSummaryDto;
import com.hms.doctor.entity.workload.WorkloadEntity;
import com.hms.doctor.mapper.workload.WorkloadMapper;
import com.hms.doctor.service.workload.WorkloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctor/workload")
@Tag(name = "Workload", description = "Quản lý khối lượng công việc của bác sĩ")
public class WorkloadController {

  @Autowired
  private WorkloadService workloadService;

  @Operation(summary = "Tạo workload mới")
  @PostMapping
  public WorkloadResponseDto createWorkload(@RequestBody WorkloadRequestDto requestDto) {
    WorkloadEntity entity = WorkloadMapper.toEntity(requestDto);
    WorkloadEntity saved = workloadService.createWorkload(entity);
    return WorkloadMapper.toDto(saved);
  }

  @Operation(summary = "Lấy toàn bộ workload")
  @GetMapping
  public List<WorkloadResponseDto> getAllWorkloads() {
    return workloadService.getAllWorkloads()
        .stream().map(WorkloadMapper::toDto)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Lấy workload theo ID")
  @GetMapping("/{id}")
  public WorkloadResponseDto getWorkloadById(@PathVariable int id) {
    return WorkloadMapper.toDto(workloadService.getWorkloadById(id));
  }

  @Operation(summary = "Lấy workload theo doctorId")
  @GetMapping("/doctor/{doctorId}")
  public List<WorkloadResponseDto> getWorkloadByDoctorId(@PathVariable int doctorId) {
    return workloadService.getWorkloadsByDoctorId(doctorId)
        .stream().map(WorkloadMapper::toDto)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Lấy workload theo khoảng thời gian")
  @GetMapping("/time-range")
  public List<WorkloadResponseDto> getWorkloadByTimeRange(
      @RequestParam String start,
      @RequestParam String end) {
    return workloadService.getWorkloadsByTimeBetween(
        LocalDateTime.parse(start), LocalDateTime.parse(end))
        .stream().map(WorkloadMapper::toDto)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Lấy workload theo doctorId và khoảng thời gian")
  @GetMapping("/doctor/{doctorId}/time-range")
  public List<WorkloadResponseDto> getWorkloadByDoctorAndTimeRange(
      @PathVariable int doctorId,
      @RequestParam String start,
      @RequestParam String end) {
    return workloadService.getWorkloadsByDoctorAndDateRange(
        doctorId, LocalDateTime.parse(start), LocalDateTime.parse(end))
        .stream().map(WorkloadMapper::toDto)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Tổng hợp workload theo khoảng thời gian")
  @GetMapping("/summary")
  public List<WorkloadSummaryDto> getSummary(
      @RequestParam String startDate,
      @RequestParam String endDate) {
    return workloadService.summarizeWorkload(
        LocalDateTime.parse(startDate), LocalDateTime.parse(endDate))
        .stream().map(WorkloadMapper::toSummaryDto)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Xóa workload theo ID")
  @DeleteMapping("/{id}")
  public void deleteWorkload(@PathVariable int id) {
    workloadService.deleteWorkload(id);
  }
}
