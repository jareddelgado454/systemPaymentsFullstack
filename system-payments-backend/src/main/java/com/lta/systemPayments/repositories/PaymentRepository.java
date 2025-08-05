package com.lta.systemPayments.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lta.systemPayments.entities.Payment;
import com.lta.systemPayments.enums.PaymentStatus;
import com.lta.systemPayments.enums.TypePayment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByStudentCode(String code);

    List<Payment> findByStatus(PaymentStatus status);

    List<Payment> findByType(TypePayment typePayment);
}
