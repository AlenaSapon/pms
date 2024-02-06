package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}