package com.example.HostelManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Data
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getHostelerId() {
        return hostelerId;
    }

    public void setHostelerId(String hostelerId) {
        this.hostelerId = hostelerId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Payment(String hostelerId, String month, double amount, LocalDate paymentDate) {
        this.hostelerId = hostelerId;
        this.month = month;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    private String hostelerId;
    private String month;
    private double amount;
    private LocalDate paymentDate;
}