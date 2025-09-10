package com.example.HostelManagement.repository;

import com.example.HostelManagement.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    int countByMonth(String month);

    boolean existsByHostelerIdAndMonth(String hostelerId, String month);

    void deleteByHostelerId(String hostelerId);

    boolean existsByHostelerId(String hostelerId);
}