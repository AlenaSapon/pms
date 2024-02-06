package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.RoleHasRoleDto;
import com.pms.patientmanagementsystem.model.HasRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleHasRoleDtoConverter {
    public RoleHasRoleDto convert(HasRole from){
        return new RoleHasRoleDto(
                from.getId(),
                from.getTimeFrom(),
                from.getTimeTo(),
                from.getIsActive(),
                from.getEmployee() != null ? from.getEmployee().getId() : null
        );
    }

    public List<RoleHasRoleDto> convert(List<HasRole> from){
        return from.stream().map(this::convert).toList();
    }
}
