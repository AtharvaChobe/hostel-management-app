package com.example.HostelManagement.service;
import com.example.HostelManagement.model.Hosteler;
import com.example.HostelManagement.model.Payment;
import com.example.HostelManagement.repository.HostelerRepository;
import com.example.HostelManagement.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private HostelerRepository hostelerRepository;

    @Autowired
    EmailService emailService;

    public Payment add(Payment payment) {
        if (paymentRepository.existsByHostelerIdAndMonth(payment.getHostelerId(), payment.getMonth())) {
            throw new IllegalArgumentException("Payment already exists for this hosteler and month.");
        }
        Payment newPayment = paymentRepository.save(payment);
        emailService.sendReceipt(newPayment);
        return newPayment;
    }

    public List<Payment> getPayments(String hostelerId) {
        List<Payment> all = paymentRepository.findAll();
//        System.out.println(all);
        List<Payment> paymentHistory = new ArrayList<>();
        for(Payment p: all){
            if(p.getHostelerId() != null && p.getHostelerId().equals(hostelerId)) paymentHistory.add(p);
        }
//        System.out.println(paymentHistory);
        return paymentHistory;
    }

    public int countPaymentByMonth(String month){
//        System.out.println(repo.countByMonth(month));
        return paymentRepository.countByMonth(month);
    }

    public List<Hosteler> findDefaultersByMonth(String month) {
        List<Hosteler> allHostelers = hostelerRepository.findAll();
        List<Hosteler> defaulters = new ArrayList<>();

        for (Hosteler hosteler : allHostelers) {
            boolean hasPaid = paymentRepository.existsByHostelerIdAndMonth(hosteler.getId(), month);
            if (!hasPaid) {
                defaulters.add(hosteler);
            }
        }
        return defaulters;
    }
}