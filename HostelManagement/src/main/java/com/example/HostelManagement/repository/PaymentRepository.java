package com.example.HostelManagement.repository;

import com.example.HostelManagement.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
//    List<Payment> findByHostelerId(String hostelerId);
}
