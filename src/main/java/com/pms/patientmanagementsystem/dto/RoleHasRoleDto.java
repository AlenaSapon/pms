package com.pms.patientmanagementsystem.dto;

import java.time.LocalDateTime;

public record RoleHasRoleDto(
        String id,
        LocalDateTime timeFrom,
        LocalDateTime timeTo,
        Boolean isActive,
        String employeeId
) {
}
