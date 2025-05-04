package com.hms.patient.repository;

import com.hms.patient.dtos.invoice.InvoiceDto;
import com.hms.patient.entity.invoice.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, String> {

    @Query("SELECT new com.hms.patient.dtos.invoice.InvoiceDto(" +
            "i.invoiceId, i.dateExport, i.cost, i.paymentDate, i.paymentMethod) " +
            "FROM InvoiceEntity i " +
            "WHERE i.paymentMethod = :paymentMethod")
    List<InvoiceDto> findByPaymentMethod(@Param("paymentMethod") String paymentMethod);

    @Query("SELECT new com.hms.patient.dtos.invoice.InvoiceDto(" +
            "i.invoiceId, i.dateExport, i.cost, i.paymentDate, i.paymentMethod) " +
            "FROM InvoiceEntity i " +
            "WHERE i.invoiceId = :id")
    Optional<InvoiceDto> findDtoById(@Param("id") String invoiceId);
}