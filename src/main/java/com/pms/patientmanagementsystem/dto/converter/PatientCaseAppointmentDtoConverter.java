package com.pms.patientmanagementsystem.dto.converter;

import com.pms.patientmanagementsystem.dto.PatientCaseAppointmentDto;
import com.pms.patientmanagementsystem.model.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientCaseAppointmentDtoConverter {
    public PatientCaseAppointmentDto convert(Appointment from) {
        return new PatientCaseAppointmentDto(
                from.getId(),
                from.getStartTime(),
                from.getEndTime(),
                from.getStatus() != null ? from.getStatus().getId() : null,
                from.getInDepartment() != null ? from.getInDepartment().getId() : null
        );
    }

    public List<PatientCaseAppointmentDto> convert(List<Appointment> from) {
        return from.stream().map(this::convert).toList();
    }
}
