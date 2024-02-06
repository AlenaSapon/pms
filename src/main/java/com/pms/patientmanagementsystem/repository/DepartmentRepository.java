package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    boolean existsByNameIgnoreCase(String name);
}