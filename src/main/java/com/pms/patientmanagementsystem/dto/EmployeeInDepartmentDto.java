package com.pms.patientmanagementsystem.dto;

import java.time.LocalDateTime;

public record EmployeeInDepartmentDto(
    String id,
    LocalDateTime timeFrom,
    LocalDateTime timeTo,
    Boolean isActive,
    String departmentId
) {
}
