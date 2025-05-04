package com.hms.patient.service.treatmentService.medicalHistoryService;

import com.hms.patient.common.PageResponse;
import com.hms.patient.dtos.treatment.medicalHistory.*;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalHistoryServiceInterface {
    /**
     * Lấy danh sách lịch sử khám bệnh dựa trên thông tin truy vấn.
     * @param queryDto thông tin truy vấn lịch sử khám bệnh
     * @return danh sách lịch sử khám bệnh
     */
    PageResponse<MedicalHistoryDto> listMedicalHistories(MedicalHistoryQueryDto queryDto);

    /**
     * Lấy thông tin chi tiết lịch sử khám bệnh dựa trên ID.
     * @param patientId ID của bệnh nhân
     * @param doctorId ID của bác sĩ
     * @param dateModify thời gian tạo/cập nhật
     * @return thông tin chi tiết lịch sử khám bệnh
     */
    MedicalHistoryDetailDto getMedicalHistoryById(int patientId, int doctorId, LocalDateTime dateModify);

    /**
     * Tạo một lịch sử khám bệnh mới.
     * @param createDto thông tin lịch sử khám bệnh
     */
    void createMedicalHistory(MedicalHistoryCreateDto createDto);

    /**
     * Cập nhật thông tin lịch sử khám bệnh.
     * @param updateDto thông tin lịch sử khám bệnh
     */
    MedicalHistoryDto updateMedicalHistory(MedicalHistoryUpdateDto updateDto);

    /**
     * Xóa lịch sử khám bệnh dựa trên ID.
     * @param patientId ID của bệnh nhân
     * @param doctorId ID của bác sĩ
     * @param dateModify thời gian tạo/cập nhật
     */
    void deleteMedicalHistory(int patientId, int doctorId, LocalDateTime dateModify);
}