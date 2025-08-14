package com.example.HostelManagement.service;
import com.example.HostelManagement.model.Payment;
import com.example.HostelManagement.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repo;

    public Payment add(Payment payment) {
        return repo.save(payment);
    }

    public List<Payment> getPayments(String hostelerId) {
        List<Payment> all = repo.findAll();
//        System.out.println(all);
        List<Payment> paymentHistory = new ArrayList<>();
        for(Payment p: all){
            if(p.getHostelerId() != null && p.getHostelerId().equals(hostelerId)) paymentHistory.add(p);
        }
//        System.out.println(paymentHistory);
        return paymentHistory;
    }
}