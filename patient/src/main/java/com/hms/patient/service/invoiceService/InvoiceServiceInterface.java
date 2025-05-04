package com.hms.patient.service.invoiceService.invoiceService;

import com.hms.patient.dtos.invoice.InvoiceDto;
import com.hms.patient.dtos.invoice.InvoiceQueryDto;

import java.util.List;

public interface InvoiceServiceInterface {
    /**
     * Lấy danh sách hóa đơn dựa trên thông tin truy vấn.
     * @param invoiceQueryDto thông tin truy vấn hóa đơn
     * @return danh sách hóa đơn
     */
    List<InvoiceDto> listInvoices(InvoiceQueryDto invoiceQueryDto);

    /**
     * Lấy thông tin hóa đơn dựa trên ID.
     * @param invoiceId ID của hóa đơn
     * @return thông tin hóa đơn
     */
    InvoiceDto getInvoiceById(String invoiceId);

    /**
     * Tạo một hóa đơn mới.
     * @param invoiceDto thông tin hóa đơn
     * @return ID của hóa đơn đã tạo
     */
    String createInvoice(InvoiceDto invoiceDto);

    /**
     * Cập nhật thông tin hóa đơn.
     * @param invoiceDto thông tin hóa đơn
     */
    void updateInvoice(InvoiceDto invoiceDto);

    /**
     * Xóa hóa đơn dựa trên ID.
     * @param invoiceId ID của hóa đơn
     */
    void deleteInvoice(String invoiceId);
}