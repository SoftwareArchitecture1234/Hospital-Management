package com.hms.patient.controller;

import com.hms.patient.dtos.treatment.symptom.SymptomDto;
import com.hms.patient.dtos.treatment.symptom.SymptomQueryDto;
import com.hms.patient.service.treatmentService.symptomService.SymptomServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;

import java.util.List;

/**
 * SymptomController xử lý các HTTP request.
 * Nó cung cấp các endpoint cho việc tạo, lấy, cập nhật và xóa thông tin triệu chứng.
 * @ symptomService là một interface cung cấp các phương thức để xử lý các yêu cầu liên quan đến triệu chứng.
 */
@Tag(
        name = "Symptom",
        description = "SymptomController xử lý các HTTP request" +
                " cho việc tạo, lấy, cập nhật và xóa thông tin triệu chứng." +
                " Nó cung cấp các endpoint cho việc quản lý dữ liệu triệu chứng trong hệ thống."
)
@RestController
@RequestMapping("/api/symptom")
public class SymptomController {
    @Autowired
    private SymptomServiceInterface symptomService;

    /**
     * Lấy danh sách triệu chứng theo tiêu chí tìm kiếm.
     * @param symptomQueryDto thông tin truy vấn triệu chứng
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và danh sách triệu chứng
     */
    @Operation(
            summary = "Lấy danh sách triệu chứng theo tiêu chí tìm kiếm",
            description = "Lấy danh sách triệu chứng theo thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "symptomQueryDto",
            description = "Thông tin truy vấn triệu chứng",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = SymptomQueryDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Danh sách triệu chứng được lấy thành công",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = SymptomDto.class
                    )
            )
    )
    @GetMapping("/list")
    public ResponseEntity<List<SymptomDto>> listSymptoms(SymptomQueryDto symptomQueryDto) {
        List<SymptomDto> symptoms = symptomService.listSymptoms(symptomQueryDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(symptoms);
    }

    /**
     * Lấy thông tin triệu chứng theo ID.
     * @param id ID của triệu chứng
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK) và thông tin triệu chứng
     */
    @Operation(
            summary = "Lấy thông tin triệu chứng theo ID",
            description = "Lấy thông tin triệu chứng theo ID được cung cấp trong path variable."
    )
    @Parameter(
            name = "id",
            description = "ID của triệu chứng cần lấy",
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thông tin triệu chứng được lấy thành công",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = SymptomDto.class
                    )
            )
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<SymptomDto> getSymptomById(@PathVariable("id") int id) {
        SymptomDto symptomDto = symptomService.getSymptomById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(symptomDto);
    }

    /**
     * Tạo một triệu chứng mới.
     * @param symptomDto thông tin triệu chứng
     * @return ResponseEntity với mã trạng thái HTTP 201 (Created) và ID của triệu chứng mới
     */
    @Operation(
            summary = "Tạo một triệu chứng mới",
            description = "Tạo một triệu chứng mới với thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "symptomDto",
            description = "Thông tin triệu chứng cần tạo",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = SymptomDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "201",
            description = "Triệu chứng được tạo thành công",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = Integer.class
                    )
            )
    )
    @PostMapping("/create")
    public ResponseEntity<String> createSymptom(@RequestBody SymptomDto symptomDto) {
        symptomService.createSymptom(symptomDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Symptom created successfully");
    }

    /**
     * Cập nhật thông tin triệu chứng.
     * @param symptomDto thông tin triệu chứng
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK)
     */
    @Operation(
            summary = "Cập nhật thông tin triệu chứng",
            description = "Cập nhật thông tin triệu chứng với thông tin được cung cấp trong request body."
    )
    @Parameter(
            name = "symptomDto",
            description = "Thông tin triệu chứng cần cập nhật",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = SymptomDto.class
                    )
            ),
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thông tin triệu chứng được cập nhật thành công",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = String.class
                    )
            )
    )
    @PutMapping("/update")
    public ResponseEntity<String> updateSymptom(@RequestBody SymptomDto symptomDto) {
        symptomService.updateSymptom(symptomDto);
        return ResponseEntity.ok("Symptom updated successfully");
    }

    /**
     * Xóa triệu chứng theo ID.
     * @param id ID của triệu chứng
     * @return ResponseEntity với mã trạng thái HTTP 200 (OK)
     */
    @Operation(
            summary = "Xóa triệu chứng theo ID",
            description = "Xóa triệu chứng theo ID được cung cấp trong path variable."
    )
    @Parameter(
            name = "id",
            description = "ID của triệu chứng cần xóa",
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Triệu chứng được xóa thành công",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = String.class
                    )
            )
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSymptom(@PathVariable("id") int id) {
        symptomService.deleteSymptom(id);
        return ResponseEntity.ok("Symptom deleted successfully");
    }
}