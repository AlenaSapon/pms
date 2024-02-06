package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.HasRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HasRoleRepository extends JpaRepository<HasRole, String> {
}