package com.hms.patient.controller;

import com.hms.patient.dtos.treatment.medicine.MedicineDto;
import com.hms.patient.dtos.treatment.medicine.MedicineQueryDto;
import com.hms.patient.service.treatmentService.medicineService.MedicineServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MedicineController xử lý các HTTP request.
 * Nó cung cấp các endpoint cho việc tạo, lấy, cập nhật và xóa thông tin thuốc.
 * @ medicineService là một interface cung cấp các phương thức để xử lý các yêu cầu liên quan đến thuốc.
 */
@Tag(
        name = "Medicine",
        description = "MedicineController xử lý các HTTP request" +
                " cho việc tạo, lấy, cập nhật và xóa thông tin thuốc." +
                " Nó cung cấp các endpoint cho việc quản lý dữ liệu thuốc trong hệ thống."
)
@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
    @Autowired
    private MedicineServiceInterface medicineService;

    /**
     * Lấy danh sách thuốc theo tiêu chí tìm kiếm.
     * @param medicineQueryDto thông tin truy vấn thuốc
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và danh sách thuốc
     */
    @Operation(
            summary = "Lấy danh sách thuốc theo tiêu chí tìm kiếm",
            description = "Lấy danh sách thuốc theo thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "medicineQueryDto",
            description = "Thông tin truy vấn thuốc",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = MedicineQueryDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Danh sách thuốc được lấy thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = MedicineDto.class
                    )
            )
    )
    @GetMapping("/list")
    public ResponseEntity<List<MedicineDto>> listMedicines(MedicineQueryDto medicineQueryDto) {
        List<MedicineDto> medicines = medicineService.listMedicines(medicineQueryDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicines);
    }

    /**
     * Lấy thông tin thuốc theo ID.
     * @param id ID của thuốc
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và thông tin thuốc
     */
    @Operation(
            summary = "Lấy thông tin thuốc theo ID",
            description = "Lấy thông tin thuốc theo ID được cung cấp trong path variable."
    )
    @Parameter(
            name = "id",
            description = "ID của thuốc cần lấy",
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thông tin thuốc được lấy thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = MedicineDto.class
                    )
            )
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<MedicineDto> getMedicineById(@PathVariable("id") int id) {
        MedicineDto medicineDto = medicineService.getMedicineById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicineDto);
    }

    /**
     * Tạo một thuốc mới.
     * @param medicineDto thông tin thuốc
     * @return ResponseEntity với mã trạng thái HTTP 201 (Created) và ID của thuốc mới
     */
    @Operation(
            summary = "Tạo một thuốc mới",
            description = "Tạo một thuốc mới với thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "medicineDto",
            description = "Thông tin thuốc cần tạo",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = MedicineDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "201",
            description = "Thuốc được tạo thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = Integer.class
                    )
            )
    )
    @PostMapping("/create")
    public ResponseEntity<String> createMedicine(@RequestBody MedicineDto medicineDto) {
        medicineService.createMedicine(medicineDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Medicine created successfully");
    }

    /**
     * Cập nhật thông tin thuốc.
     * @param medicineDto thông tin thuốc
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK)
     */
    @Operation(
            summary = "Cập nhật thông tin thuốc",
            description = "Cập nhật thông tin thuốc với thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "medicineDto",
            description = "Thông tin thuốc cần cập nhật",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = MedicineDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thông tin thuốc được cập nhật thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = String.class
                    )
            )
    )
    @PutMapping("/update")
    public ResponseEntity<String> updateMedicine(@RequestBody MedicineDto medicineDto) {
        medicineService.updateMedicine(medicineDto);
        return ResponseEntity.ok("Medicine updated successfully");
    }

    /**
     * Xóa thuốc theo ID.
     * @param id ID của thuốc
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK)
     */
    @Operation(
            summary = "Xóa thuốc theo ID",
            description = "Xóa thuốc theo ID được cung cấp trong path variable."
    )
    @Parameter(
            name = "id",
            description = "ID của thuốc cần xóa",
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thuốc được xóa thành công",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                            implementation = String.class
                    )
            )
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable("id") int id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok("Medicine deleted successfully");
    }
}