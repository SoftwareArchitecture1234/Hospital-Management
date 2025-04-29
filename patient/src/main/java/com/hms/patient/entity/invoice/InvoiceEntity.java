package com.hms.patient.entity.invoice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "Invoice")
@Entity
public class InvoiceEntity {
    @Id
    @Column(name = "invoice_id")
    private int invoiceId;

    @Column(name = "date_export")
    private LocalDateTime dateExport;

    @Column(name = "cost")
    private double cost;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;
}
