package com.lta.systemPayments.entities;

import java.time.LocalDate;

import com.lta.systemPayments.enums.PaymentStatus;
import com.lta.systemPayments.enums.TypePayment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Id;

    private LocalDate date;

    private double amount;

    private TypePayment type;

    private PaymentStatus status;

    private String file;

    private Student student;
}
