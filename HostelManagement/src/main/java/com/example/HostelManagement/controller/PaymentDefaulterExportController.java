package com.example.HostelManagement.controller;

import com.example.HostelManagement.service.PaymentDefaulterExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class PaymentDefaulterExportController {
    @Autowired
    private PaymentDefaulterExportService paymentDefaulterExportService;

    @GetMapping("/api/payment/defaulter/{month}/{year}")
    public ResponseEntity<byte[]> exportDefaulters(@PathVariable String month, @PathVariable int year) {
        try {

            ByteArrayInputStream excel = paymentDefaulterExportService.exportData(month, year);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=payment_defaulters" + month + ".xlsx");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excel.readAllBytes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
