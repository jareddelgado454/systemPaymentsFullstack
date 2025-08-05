package com.lta.systemPayments.services;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lta.systemPayments.entities.Payment;
import com.lta.systemPayments.entities.Student;
import com.lta.systemPayments.enums.PaymentStatus;
import com.lta.systemPayments.enums.TypePayment;
import com.lta.systemPayments.repositories.PaymentRepository;
import com.lta.systemPayments.repositories.StudentRepository;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Payment savePayment(MultipartFile file, double amount, TypePayment type, LocalDate date, String studentCode) throws IOException{
        Path folderPath = Paths.get(System.getProperty("user.home"),"enset-data", "payments");
        
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }

        String fileName = UUID.randomUUID().toString();

        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data","payments",fileName+".pdf");
        
        Files.copy(file.getInputStream(), filePath);

        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
        .type(type)
        .status(PaymentStatus.CREATED)
        .date(date)
        .student(student)
        .amount(amount)
        .file(filePath.toUri().toString())
        .build();

        return paymentRepository.save(payment);
    }

    public byte[] getFileById(Long paymentId) throws IOException{

        Payment payment = paymentRepository.findById(paymentId).get();

        return Files.readAllBytes(Path.of(URI.create(payment.getFile()))); 
    }

    public Payment updateStatusPayment(PaymentStatus paymentStatus, Long id){
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(paymentStatus);
        return paymentRepository.save(payment);
    }
}
