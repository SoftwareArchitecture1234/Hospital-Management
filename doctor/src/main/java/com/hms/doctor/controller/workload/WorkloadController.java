package com.hms.doctor.controller.workload;

import com.hms.doctor.dto.workload.WorkloadDTO;
import com.hms.doctor.dto.workload.WorkloadSummaryDTO;
import com.hms.doctor.entity.workload.WorkloadEntity;
import com.hms.doctor.mapper.workload.WorkloadMapper;
import com.hms.doctor.service.workload.WorkloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * WorkloadController xử lý các HTTP request liên quan đến ca làm việc của bác
 * sĩ.
 * Nó cung cấp các endpoint cho việc tạo, lấy theo ID, lấy theo bác sĩ, tổng hợp
 * dữ liệu workload và xoá.
 */
@Tag(name = "Workload", description = "WorkloadController xử lý các HTTP request cho việc tạo, lấy, tổng hợp và xoá thông tin lịch làm việc của bác sĩ.")
@RestController
@RequestMapping("/api/workload")
public class WorkloadController {

  @Autowired
  private WorkloadService workloadService;

  /**
   * Tạo một workload mới.
   * 
   * @param workloadDTO thông tin workload
   * @return ResponseEntity với mã trạng thái HTTP 201 (Created)
   */
  @Operation(summary = "Tạo một workload mới", description = "Tạo một ca làm việc mới với thông tin được cung cấp trong request body.")
  @Parameter(name = "workloadDTO", description = "Thông tin workload cần tạo", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkloadDTO.class)))
  @ApiResponse(responseCode = "201", description = "Workload được tạo thành công", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
  @PostMapping("/create")
  public ResponseEntity<String> createWorkload(@RequestBody WorkloadDTO workloadDTO) {
    WorkloadEntity entity = WorkloadMapper.toEntity(workloadDTO);
    workloadService.createWorkload(entity);
    return ResponseEntity.status(201).body("Workload created successfully");
  }

  /**
   * Lấy workload theo ID.
   * 
   * @param id ID workload
   * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và thông tin workload
   */
  @Operation(summary = "Lấy workload theo ID", description = "Lấy thông tin workload theo ID được cung cấp trong path variable.")
  @ApiResponse(responseCode = "200", description = "Workload được lấy thành công", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkloadDTO.class)))
  @GetMapping("/{id}")
  public ResponseEntity<WorkloadDTO> getWorkloadById(
      @Parameter(description = "ID của workload") @PathVariable Long id) {
    WorkloadEntity entity = workloadService.getWorkloadById(id);
    return ResponseEntity.ok(WorkloadMapper.toDTO(entity));
  }

  /**
   * Lấy toàn bộ workload theo doctorId.
   * 
   * @param doctorId ID của bác sĩ
   * @return Danh sách workload của bác sĩ
   */
  @Operation(summary = "Lấy toàn bộ workload theo doctorId", description = "Trả về danh sách tất cả workload của bác sĩ với doctorId được cung cấp.")
  @ApiResponse(responseCode = "200", description = "Danh sách workload được lấy thành công", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkloadDTO.class)))
  @GetMapping("/doctor/{doctorId}")
  public ResponseEntity<List<WorkloadDTO>> getWorkloadByDoctorId(
      @Parameter(description = "ID của bác sĩ") @PathVariable Integer doctorId) {
    List<WorkloadEntity> list = workloadService.getWorkloadsByDoctorId(doctorId);
    return ResponseEntity.ok(WorkloadMapper.toDTOList(list));
  }

  /**
   * Lấy workload của bác sĩ trong khoảng thời gian.
   * 
   * @param doctorId ID của bác sĩ
   * @param start    ngày bắt đầu
   * @param end      ngày kết thúc
   * @return danh sách workload trong khoảng ngày
   */
  @Operation(summary = "Lấy workload theo doctorId và khoảng thời gian", description = "Lấy workload của một bác sĩ trong khoảng thời gian từ start đến end.")
  @ApiResponse(responseCode = "200", description = "Danh sách workload được lấy thành công", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkloadDTO.class)))
  @GetMapping("/doctor/{doctorId}/range")
  public ResponseEntity<List<WorkloadDTO>> getWorkloadByDoctorAndRange(
      @Parameter(description = "ID bác sĩ") @PathVariable Integer doctorId,
      @Parameter(description = "Ngày bắt đầu") @RequestParam LocalDate start,
      @Parameter(description = "Ngày kết thúc") @RequestParam LocalDate end) {
    List<WorkloadEntity> list = workloadService.getWorkloadsByDoctorAndDateRange(doctorId, start, end);
    return ResponseEntity.ok(WorkloadMapper.toDTOList(list));
  }

  /**
   * Tổng hợp số lượt và thời gian làm việc theo khoảng thời gian.
   * 
   * @param start ngày bắt đầu
   * @param end   ngày kết thúc
   * @return danh sách tổng hợp theo bác sĩ
   */
  @Operation(summary = "Tổng hợp workload theo khoảng thời gian", description = "Trả về số lượt làm việc và tổng thời gian làm việc của các bác sĩ trong khoảng thời gian.")
  @ApiResponse(responseCode = "200", description = "Tổng hợp workload thành công", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkloadSummaryDTO.class)))
  @GetMapping("/summary")
  public ResponseEntity<List<WorkloadSummaryDTO>> getWorkloadSummary(
      @Parameter(description = "Ngày bắt đầu") @RequestParam LocalDate start,
      @Parameter(description = "Ngày kết thúc") @RequestParam LocalDate end) {
    List<Object[]> summaryData = workloadService.summarizeWorkload(start, end);
    return ResponseEntity.ok(WorkloadMapper.toSummaryDTOList(summaryData));
  }

  /**
   * Xoá workload theo ID.
   * 
   * @param id ID workload cần xoá
   * @return phản hồi thành công
   */
  @Operation(summary = "Xoá workload theo ID", description = "Xoá workload với ID được cung cấp trong path.")
  @ApiResponse(responseCode = "200", description = "Workload xoá thành công", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteWorkload(
      @Parameter(description = "ID workload") @PathVariable Long id) {
    workloadService.deleteWorkload(id);
    return ResponseEntity.ok("Workload deleted successfully");
  }
}
