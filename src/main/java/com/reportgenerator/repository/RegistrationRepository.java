package com.reportgenerator.repository;

import com.reportgenerator.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration,Long> {


}
