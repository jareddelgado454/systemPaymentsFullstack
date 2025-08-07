package com.lta.systemPayments;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lta.systemPayments.entities.Payment;
import com.lta.systemPayments.entities.Student;
import com.lta.systemPayments.enums.PaymentStatus;
import com.lta.systemPayments.enums.TypePayment;
import com.lta.systemPayments.repositories.PaymentRepository;
import com.lta.systemPayments.repositories.StudentRepository;

@SpringBootApplication
public class SystemPaymentsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemPaymentsBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
		return args -> {
			studentRepository.save(Student.builder()
			.id(UUID.randomUUID().toString())
			.name("Jared")
			.lastname("Delgado")
			.code("1234")
			.programId("LTA1")
			.build());

			studentRepository.save(Student.builder()
			.id(UUID.randomUUID().toString())
			.name("Ariana")
			.lastname("Caceres")
			.code("2233")
			.programId("LTA2")
			.build());
			
			studentRepository.save(Student.builder()
			.id(UUID.randomUUID().toString())
			.name("Manuel")
			.lastname("Solis")
			.code("4321")
			.programId("LTA1")
			.build());

			TypePayment typesPayment[] = TypePayment.values();
			Random random = new Random();

			studentRepository.findAll().forEach(student -> {
				for(int i = 0; i < 10; i++ ){
					int index = random.nextInt(typesPayment.length);
					Payment payment = Payment.builder()
								.amount(1000+ (int)(Math.random()*20000))
								.type(typesPayment[index])
								.status(PaymentStatus.CREATED)
								.date(LocalDate.now())
								.student(student)
								.build();
					paymentRepository.save(payment);
				}
			});

		};
	}

}
