package com.lta.systemPayments.dtos;

import java.time.LocalDate;

import com.lta.systemPayments.enums.TypePayment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class newPaymentDto {
    

    private double amount;

    private TypePayment type;

    private LocalDate date;

    private String studentCode;
}
