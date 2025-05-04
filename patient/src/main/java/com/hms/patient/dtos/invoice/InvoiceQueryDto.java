package com.hms.patient.dtos.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(
        name = "InvoiceQueryDto",
        description = "Đối tượng này dùng để truy vấn thông tin hóa đơn."
)
@Getter
@Setter
public class InvoiceQueryDto {
    @Schema(
            name = "paymentMethod",
            description = "Phương thức thanh toán",
            example = "CREDIT_CARD"
    )
    private String paymentMethod;
}