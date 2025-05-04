package com.hms.patient.dtos.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(
        name = "InvoiceDto",
        description = "Đối tượng này đại diện cho thông tin hóa đơn trong hệ thống."
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDto {
    @Schema(
            name = "invoiceId",
            description = "ID của hóa đơn",
            example = "INV-20250502-001"
    )
    private String invoiceId;

    @Schema(
            name = "dateExport",
            description = "Ngày xuất hóa đơn",
            example = "2025-05-02T10:30:00"
    )
    private LocalDateTime dateExport;

    @Schema(
            name = "cost",
            description = "Chi phí",
            example = "1500000"
    )
    private double cost;

    @Schema(
            name = "paymentDate",
            description = "Ngày thanh toán",
            example = "2025-05-02T14:45:00"
    )
    private LocalDateTime paymentDate;

    @Schema(
            name = "paymentMethod",
            description = "Phương thức thanh toán",
            example = "CREDIT_CARD"
    )
    private String paymentMethod;
}