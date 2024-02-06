package com.pms.patientmanagementsystem.dto;

import java.time.LocalDateTime;

public record AppointmentDto(
    String id,
    LocalDateTime startTime,
    LocalDateTime endTime,
    String statusId,
    String patientCaseId,
    String inDepartmentId
) {
}
