package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, String> {
}