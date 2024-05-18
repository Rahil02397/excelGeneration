package com.reportgenerator.controller;

import com.reportgenerator.entity.Registration;
import com.reportgenerator.service.ExcelGenerator;
import com.reportgenerator.service.RegistrationService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class RegistrationController {

    private RegistrationService registrationService;
    private ExcelGenerator excelGenerator;

    public RegistrationController(RegistrationService registrationService, ExcelGenerator excelGenerator) {
        this.registrationService = registrationService;

        this.excelGenerator = excelGenerator;
    }

    @PostMapping("/save-registration")
    public ResponseEntity<?> saveRegistration(@RequestBody List<Registration> registrationsList){
        registrationService.saveRegistration(registrationsList);
        return new ResponseEntity<>("Data Saved Successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/get-registrations")
    public ResponseEntity<?> getRegistrations() throws IOException {
        List<Registration> registrations = registrationService.getAllRegistrations();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }


    //hit this API from any browser to download the excel sheet;
    @GetMapping("/get-excel")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
        ByteArrayOutputStream out = excelGenerator.generateExcel();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=registration.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
        }




}
