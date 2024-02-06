package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.AppointmentDto;
import com.pms.patientmanagementsystem.model.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentDtoConverter {
    public AppointmentDto convert(Appointment from) {
        return new AppointmentDto(
                from.getId(),
                from.getStartTime(),
                from.getEndTime(),
                from.getStatus() != null ? from.getStatus().getId() : null,
                from.getPatientCase() != null ? from.getPatientCase().getId() : null,
                from.getInDepartment() != null ? from.getInDepartment().getId() : null
        );
    }

    public List<AppointmentDto> convert(List<Appointment> from) {
        return from.stream().map(this::convert).toList();
    }
}
