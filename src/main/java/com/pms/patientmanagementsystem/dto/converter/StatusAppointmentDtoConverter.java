package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.StatusAppointmentDto;
import com.pms.patientmanagementsystem.model.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusAppointmentDtoConverter {
    public StatusAppointmentDto convert(Appointment from) {
        return new StatusAppointmentDto(
                from.getId(),
                from.getStartTime(),
                from.getEndTime(),
                from.getPatientCase() != null ? from.getPatientCase().getId() : null,
                from.getInDepartment() != null ? from.getInDepartment().getId() : null
        );
    }

    public List<StatusAppointmentDto> convert(List<Appointment> from) {
        return from.stream().map(this::convert).toList();
    }
}
