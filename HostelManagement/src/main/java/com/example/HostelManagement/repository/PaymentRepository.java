package com.example.HostelManagement.repository;

import com.example.HostelManagement.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    int countByMonthAndYear(String month, int year);

    boolean existsByHostelerIdAndMonthAndYear(String hostelerId, String month, int year);

    void deleteByHostelerId(String hostelerId);

    boolean existsByHostelerId(String hostelerId);
}