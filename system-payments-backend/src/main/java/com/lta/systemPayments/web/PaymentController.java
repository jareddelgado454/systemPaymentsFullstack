package com.lta.systemPayments.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lta.systemPayments.entities.Payment;
import com.lta.systemPayments.entities.Student;
import com.lta.systemPayments.enums.PaymentStatus;
import com.lta.systemPayments.enums.TypePayment;
import com.lta.systemPayments.repositories.PaymentRepository;
import com.lta.systemPayments.repositories.StudentRepository;
import com.lta.systemPayments.services.PaymentService;

@RestController
@CrossOrigin("*")
public class PaymentController {
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/students")
    public List<Student> ListStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/students/{code}")
    public Student listStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }

    @GetMapping("/studentsByProgram")
    public List<Student> listStudentsByProgram(@RequestParam String programId){
        return studentRepository.findByProgramId(programId);
    }

    @GetMapping("/payments")
    public List<Payment> listPayment(){
        return paymentRepository.findAll();
    }

    @GetMapping("/payments/{id}")
    public Payment listPaymentById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }

    @GetMapping("/students/{code}/payments")
    public List<Payment> listPaymentByStudentCode(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping("/paymentsByStatus")
    public List<Payment> listPaymentByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }

    @GetMapping("/payments/byType")
    public List<Payment> listPaymentsByType(@RequestParam TypePayment type){
        return paymentRepository.findByType(type);
    }

    @PutMapping("/payments/{paymentId}/updatePayment")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status, @PathVariable Long paymentId){
        return paymentService.updateStatusPayment(status, paymentId);
    }

    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, double amount, TypePayment type, LocalDate date, String studentCode) throws IOException{
        return paymentService.savePayment(file, amount, type, date, studentCode);
    }

    @GetMapping(value = "/paymentFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] listFileById(@PathVariable Long paymentId) throws IOException{
        return paymentService.getFileById(paymentId);
    }
}
