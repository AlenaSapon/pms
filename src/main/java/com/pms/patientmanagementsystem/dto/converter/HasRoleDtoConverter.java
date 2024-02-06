package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.HasRoleDto;
import com.pms.patientmanagementsystem.model.HasRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HasRoleDtoConverter {
    public HasRoleDto convert(HasRole from) {
        return new HasRoleDto(
                from.getId(),
                from.getTimeFrom(),
                from.getTimeTo(),
                from.getIsActive(),
                from.getRole() != null ? from.getRole().getId() : null,
                from.getEmployee() != null ? from.getEmployee().getId() : null
        );
    }

    public List<HasRoleDto> convert(List<HasRole> from) {
        return from.stream().map(this::convert).toList();
    }
}
