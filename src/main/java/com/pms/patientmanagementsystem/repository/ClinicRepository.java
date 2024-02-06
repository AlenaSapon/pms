package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, String> {
    boolean existsByNameIgnoreCase(String name);
}