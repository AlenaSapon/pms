package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, String> {
    boolean existsByNameIgnoreCase(String name);
}