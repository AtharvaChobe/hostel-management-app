package com.example.HostelManagement.controller;

import com.example.HostelManagement.model.Hosteler;
import com.example.HostelManagement.model.Payment;
import com.example.HostelManagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hostelers")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<?> addPayment(@RequestBody Payment payment) {
        try {
            Payment addedPayment = paymentService.add(payment);
            return ResponseEntity.ok(addedPayment);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Payment already exists"));
        }
    }

    @GetMapping("/{id}/payments")
    public ResponseEntity<List<Payment>> getPayments(@PathVariable String id) {
        return ResponseEntity.ok(paymentService.getPayments(id));
    }

    @GetMapping("/month/{month}/{year}")
    public ResponseEntity<Integer> countByPaymentMonth(@PathVariable String month, @PathVariable int year) {
        return new ResponseEntity<>(paymentService.countPaymentByMonthandYear(month, year), HttpStatus.OK);
    }
    @GetMapping("/defaulters/{month}/{year}")
    public ResponseEntity<List<Hosteler>> getDefaulters(@PathVariable String month, @PathVariable int year) {
        List<Hosteler> defaulters = paymentService.findDefaultersByMonth(month, year);
        return ResponseEntity.ok(defaulters);
    }
}
