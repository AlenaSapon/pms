package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, String> {
}