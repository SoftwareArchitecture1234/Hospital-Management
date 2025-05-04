package com.hms.patient.mapper.invoice;

import com.hms.patient.dtos.invoice.InvoiceDto;
import com.hms.patient.entity.invoice.InvoiceEntity;

public class InvoiceMapper {
    public static InvoiceEntity toEntity(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setInvoiceId(invoiceDto.getInvoiceId());
        invoiceEntity.setDateExport(invoiceDto.getDateExport());
        invoiceEntity.setCost(invoiceDto.getCost());
        invoiceEntity.setPaymentDate(invoiceDto.getPaymentDate());
        invoiceEntity.setPaymentMethod(invoiceDto.getPaymentMethod());

        return invoiceEntity;
    }

    public static InvoiceDto toDto(InvoiceEntity invoiceEntity) {
        if (invoiceEntity == null) {
            return null;
        }

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceId(invoiceEntity.getInvoiceId());
        invoiceDto.setDateExport(invoiceEntity.getDateExport());
        invoiceDto.setCost(invoiceEntity.getCost());
        invoiceDto.setPaymentDate(invoiceEntity.getPaymentDate());
        invoiceDto.setPaymentMethod(invoiceEntity.getPaymentMethod());

        return invoiceDto;
    }
}