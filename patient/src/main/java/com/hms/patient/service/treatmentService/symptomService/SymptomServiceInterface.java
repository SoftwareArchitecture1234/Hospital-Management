package com.hms.patient.service.treatmentService.symptomService;

import com.hms.patient.dtos.treatment.symptom.SymptomDto;
import com.hms.patient.dtos.treatment.symptom.SymptomQueryDto;

import java.util.List;

public interface SymptomServiceInterface {
    /**
     * Lấy danh sách triệu chứng dựa trên thông tin truy vấn.
     * @param symptomQueryDto thông tin truy vấn triệu chứng
     * @return danh sách triệu chứng
     */
    List<SymptomDto> listSymptoms(SymptomQueryDto symptomQueryDto);

    /**
     * Lấy thông tin triệu chứng dựa trên ID.
     * @param symptomId ID của triệu chứng
     * @return thông tin triệu chứng
     */
    SymptomDto getSymptomById(int symptomId);

    /**
     * Tạo một triệu chứng mới.
     * @param symptomDto thông tin triệu chứng
     * @return ID của triệu chứng đã tạo
     */
    void createSymptom(SymptomDto symptomDto);

    /**
     * Cập nhật thông tin triệu chứng.
     * @param symptomDto thông tin triệu chứng
     */
    void updateSymptom(SymptomDto symptomDto);

    /**
     * Xóa triệu chứng dựa trên ID.
     * @param symptomId ID của triệu chứng
     */
    void deleteSymptom(int symptomId);
}