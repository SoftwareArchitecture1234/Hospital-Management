package com.hms.patient.service.invoiceService.impl;

import com.hms.patient.dtos.invoice.InvoiceDto;
import com.hms.patient.dtos.invoice.InvoiceQueryDto;
import com.hms.patient.entity.invoice.InvoiceEntity;
import com.hms.patient.exception.ExceptionResourceNotFound;
import com.hms.patient.mapper.invoice.InvoiceMapper;
import com.hms.patient.repository.InvoiceRepository;
import com.hms.patient.service.invoiceService.InvoiceServiceInterface;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceServiceImply implements InvoiceServiceInterface {

    private final InvoiceRepository invoiceRepository;
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public List<InvoiceDto> listInvoices(InvoiceQueryDto invoiceQueryDto) {
        String paymentMethod = invoiceQueryDto.getPaymentMethod();

        if (paymentMethod != null && !paymentMethod.isEmpty()) {
            return invoiceRepository.findByPaymentMethod(paymentMethod);
        }

        return invoiceRepository.findAll()
                .stream()
                .map(InvoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto getInvoiceById(String invoiceId) {
        return invoiceRepository.findDtoById(invoiceId)
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "Invoice",
                        "invoiceId",
                        invoiceId
                ));
    }

    @Override
    public String createInvoice(InvoiceDto invoiceDto) {
        // Generate invoice ID if not provided
        if (invoiceDto.getInvoiceId() == null || invoiceDto.getInvoiceId().isEmpty()) {
            invoiceDto.setInvoiceId(generateInvoiceId());
        }

        // Set export date if not provided
        if (invoiceDto.getDateExport() == null) {
            invoiceDto.setDateExport(LocalDateTime.now());
        }

        InvoiceEntity invoiceEntity = InvoiceMapper.toEntity(invoiceDto);
        if (invoiceEntity != null) {
            InvoiceEntity savedEntity = invoiceRepository.save(invoiceEntity);
            return savedEntity.getInvoiceId();
        } else {
            throw new IllegalArgumentException("Invoice entity cannot be null");
        }
    }

    @Override
    public void updateInvoice(InvoiceDto invoiceDto) {
        InvoiceEntity invoiceEntity = invoiceRepository
                .findById(invoiceDto.getInvoiceId())
                .orElseThrow(() -> new ExceptionResourceNotFound(
                        "Invoice",
                        "invoiceId",
                        invoiceDto.getInvoiceId()
                ));

        updateInvoiceFields(invoiceDto, invoiceEntity);
        invoiceRepository.save(invoiceEntity);
    }

    @Override
    public void deleteInvoice(String invoiceId) {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new ExceptionResourceNotFound(
                    "Invoice",
                    "invoiceId",
                    invoiceId
            );
        }

        invoiceRepository.deleteById(invoiceId);
    }

    private void updateInvoiceFields(InvoiceDto invoiceDto, InvoiceEntity invoiceEntity) {
        if (invoiceDto.getDateExport() != null) {
            invoiceEntity.setDateExport(invoiceDto.getDateExport());
        }

        if (invoiceDto.getCost() > 0) {
            invoiceEntity.setCost(invoiceDto.getCost());
        }

        if (invoiceDto.getPaymentDate() != null) {
            invoiceEntity.setPaymentDate(invoiceDto.getPaymentDate());
        }

        if (invoiceDto.getPaymentMethod() != null && !invoiceDto.getPaymentMethod().isEmpty()) {
            invoiceEntity.setPaymentMethod(invoiceDto.getPaymentMethod());
        }
    }

    /**
     * Generates a unique invoice ID in the format: INV-YYYYMMDD-XXX
     * where XXX is a sequential number that resets daily
     *
     * @return generated invoice ID
     */
    private String generateInvoiceId() {
        LocalDate today = LocalDate.now();
        String datePart = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Get counter value and increment
        int sequenceNum = counter.incrementAndGet();

        // Format with leading zeros (001, 002, etc.)
        String sequencePart = String.format("%03d", sequenceNum);

        return "INV-" + datePart + "-" + sequencePart;
    }
}