package com.example.HostelManagement.service;

import com.example.HostelManagement.model.Hosteler;
import com.example.HostelManagement.repository.HostelerRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class HostelerDataExportService {
    @Autowired
    private HostelerRepository hostelerRepository;

    public ByteArrayInputStream exportData() throws IOException {
        List<Hosteler> all = hostelerRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Sheet sheet = workbook.createSheet("Payments");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Hosteler ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("email");
            header.createCell(3).setCellValue("Phone number");
            header.createCell(4).setCellValue("Room Number");

            int rowIdx = 1;
            for (Hosteler hosteler : all) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(hosteler.getId());
                row.createCell(1).setCellValue(hosteler.getName());
                row.createCell(2).setCellValue(hosteler.getEmail());
                row.createCell(3).setCellValue(hosteler.getContact());
                row.createCell(4).setCellValue(hosteler.getRoomNo());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            workbook.close();
            out.close();
        }
    }
}

