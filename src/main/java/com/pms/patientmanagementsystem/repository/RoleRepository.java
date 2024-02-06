package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByNameIgnoreCase(String name);
}