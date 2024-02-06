package com.pms.patientmanagementsystem.dto;

import java.util.List;

public record RoleDto(
    String id,
    String name,
    List<RoleHasRoleDto> hasRoles
) {
}
