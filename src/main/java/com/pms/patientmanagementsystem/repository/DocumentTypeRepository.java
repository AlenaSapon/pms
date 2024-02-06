package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, String> {
    boolean existsByNameIgnoreCase(String name);
}