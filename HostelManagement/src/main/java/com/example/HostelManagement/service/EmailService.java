package com.example.HostelManagement.service;

import com.example.HostelManagement.model.Hosteler;
import com.example.HostelManagement.model.Payment;
import com.example.HostelManagement.repository.HostelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private HostelerRepository hostelerRepository;

    public void sendReceipt(Payment payment) {
        SimpleMailMessage message = new SimpleMailMessage();
        Optional<Hosteler> hostelerOpt = hostelerRepository.findById(payment.getHostelerId());
//        System.out.println(payment);
        if (hostelerOpt.isPresent()) {
            Hosteler hosteler = hostelerOpt.get();
            message.setFrom("atharvachobe72@gmail.com");
            message.setTo(hosteler.getEmail());
            message.setSubject("Payment receipt for : " + payment.getMonth());
            message.setText("Hi, " + hosteler.getName() + "\n\nYour payment was successful for the month of : \""
                    + payment.getMonth() + "\n\". Your payment id is : " + payment.getId()
                    + ".\n\nNote: This is a system generated email and can be used as proof of payment");
            javaMailSender.send(message);
        } else {
            throw new RuntimeException("Hosteler not found");
        }
    }
}