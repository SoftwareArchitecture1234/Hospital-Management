package com.hms.patient.service.treatmentService.medicineService;

import com.hms.patient.dtos.treatment.medicine.MedicineDto;
import com.hms.patient.dtos.treatment.medicine.MedicineQueryDto;

import java.util.List;

public interface MedicineServiceInterface {
    /**
     * Lấy danh sách thuốc dựa trên thông tin truy vấn.
     * @param medicineQueryDto thông tin truy vấn thuốc
     * @return danh sách thuốc
     */
    List<MedicineDto> listMedicines(MedicineQueryDto medicineQueryDto);

    /**
     * Lấy thông tin thuốc dựa trên ID.
     * @param medicineId ID của thuốc
     * @return thông tin thuốc
     */
    MedicineDto getMedicineById(int medicineId);

    /**
     * Tạo một thuốc mới.
     * @param medicineDto thông tin thuốc
     * @return ID của thuốc đã tạo
     */
    void createMedicine(MedicineDto medicineDto);

    /**
     * Cập nhật thông tin thuốc.
     * @param medicineDto thông tin thuốc
     */
    void updateMedicine(MedicineDto medicineDto);

    /**
     * Xóa thuốc dựa trên ID.
     * @param medicineId ID của thuốc
     */
    void deleteMedicine(int medicineId);
}