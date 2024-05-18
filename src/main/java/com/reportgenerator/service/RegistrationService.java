package com.reportgenerator.service;

import com.reportgenerator.entity.Registration;
import com.reportgenerator.repository.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ExcelGenerator excelGenerator;

    public RegistrationService(RegistrationRepository registrationRepository, ExcelGenerator excelGenerator) {
        this.registrationRepository = registrationRepository;
        this.excelGenerator = excelGenerator;
    }

    @Async
    @Transactional
    public void saveRegistration(List<Registration> registration) {
        registrationRepository.saveAll(registration);
    }

    public List<Registration> getAllRegistrations() {
       return registrationRepository.findAll();
    }



}
