package com.reportgenerator.service;

import com.reportgenerator.entity.Registration;
import com.reportgenerator.repository.RegistrationRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelGenerator {

    private RegistrationRepository registrationRepository;

    public ExcelGenerator(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }


    public ByteArrayOutputStream generateExcel() throws IOException {
        List<Registration> registration = registrationRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Registrations");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("FirstName");
            headerRow.createCell(2).setCellValue("LastName");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("Phone");
            headerRow.createCell(5).setCellValue("Address");

            int rowIdx = 1;
            for (Registration reg : registration) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(reg.getId());
                row.createCell(1).setCellValue(reg.getFirstName());
                row.createCell(2).setCellValue(reg.getLastName());
                row.createCell(3).setCellValue(reg.getEmail());
                row.createCell(4).setCellValue(reg.getMobileNumber());
                row.createCell(5).setCellValue(reg.getAddress());
            }

            workbook.write(out);
            return out;
        }
    }

}
