package com.pms.patientmanagementsystem.dto;

import java.time.LocalDateTime;

public record EmployeeHasRoleDto(
    String id,
    LocalDateTime timeFrom,
    LocalDateTime timeTo,
    Boolean isActive,
    String roleId
) {
}
