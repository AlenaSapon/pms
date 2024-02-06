package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    boolean existsByUsername(String username);
}