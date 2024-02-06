package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.InDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InDepartmentRepository extends JpaRepository<InDepartment, String> {
}