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

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", hostelerId='" + hostelerId + '\'' +
                ", month='" + month + '\'' +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
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

    private String hostelerId;
    private String month;
    private int year;
    private double amount;
    private LocalDate paymentDate;

    public int getYear() {
        return year;
    }

    public Payment(String id, LocalDate paymentDate, double amount, int year, String month, String hostelerId) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.year = year;
        this.month = month;
        this.hostelerId = hostelerId;
    }

    public void setYear(int year) {
        this.year = year;
    }
}