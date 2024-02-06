package com.pms.patientmanagementsystem.repository;

import com.pms.patientmanagementsystem.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
}