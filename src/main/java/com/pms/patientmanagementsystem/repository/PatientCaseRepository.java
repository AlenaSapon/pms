package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.PatientCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientCaseRepository extends JpaRepository<PatientCase, String> {
}